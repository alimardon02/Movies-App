package uz.gita.moviesapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Query
import uz.gita.moviesapp.data.sources.common.models.MoviesData
import uz.gita.moviesapp.utils.ResultData

interface MoviesUseCase {
    /**
     * Movies All
     */
    fun getAllMovies(apiKey: String, page: Int): Flow<Result<MoviesData>>

    /**
     * Search movies list
     */
    fun getAllSearchMovies(apiKey: String, query: String,page: Int): Flow<Result<MoviesData>>
}