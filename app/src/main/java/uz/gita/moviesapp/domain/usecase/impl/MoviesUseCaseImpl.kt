package uz.gita.moviesapp.domain.usecase.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import uz.gita.moviesapp.data.repository.MoviesRepository
import uz.gita.moviesapp.data.sources.common.models.ErrorMessage
import uz.gita.moviesapp.data.sources.common.models.MoviesData
import uz.gita.moviesapp.domain.usecase.MoviesUseCase
import uz.gita.moviesapp.utils.ResultData
import uz.gita.moviesapp.utils.isSuccess
import javax.inject.Inject


class MoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val gson: Gson
) : MoviesUseCase {
    override fun getAllMovies(apiKey: String, page: Int): Flow<Result<MoviesData>> = flow {
        val response = moviesRepository.getAllMovies(apiKey, page)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it))
            }
        } else {
            var error = ErrorMessage(404, "Unknown error")
            response.errorBody()?.string()?.let {
                error = gson.fromJson(it, ErrorMessage::class.java)
            }
            emit(Result.failure(Exception(error.message)))
        }
    }

    override fun getAllSearchMovies(
        apiKey: String,
        query: String,
        page: Int
    ): Flow<Result<MoviesData>> = flow {
        val response = moviesRepository.getSearchMovies(apiKey, query, page)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it))
            }
        } else {
            var error = ErrorMessage(404, "Unknown error")
            response.errorBody()?.string()?.let {
                error = gson.fromJson(it, ErrorMessage::class.java)
            }
            emit(Result.failure(Exception(error.message)))
        }
    }
}