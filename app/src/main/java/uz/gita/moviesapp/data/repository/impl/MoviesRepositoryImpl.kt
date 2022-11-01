package uz.gita.moviesapp.data.repository.impl

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.gita.moviesapp.data.repository.MoviesRepository
import uz.gita.moviesapp.data.sources.common.models.MoviesData
import uz.gita.moviesapp.data.sources.common.models.Results
import uz.gita.moviesapp.data.sources.remote.api.MoviesApi
import uz.gita.moviesapp.presentation.ui.adapter.PagingSource
import uz.gita.moviesapp.utils.ResultData
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MoviesRepositoryImpl @Inject constructor(
    private val moviesAPi: MoviesApi,
) : MoviesRepository {


    override suspend fun getAllMovies(apiKey: String, page: Int): Response<MoviesData> {

        return moviesAPi.getMoviesList(apiKey, page)
    }

    override suspend fun getSearchMovies(
        apiKey: String,
        query: String,
        page: Int
    ): Response<MoviesData> {
        return moviesAPi.searchMovies(apiKey, query, page)
    }

}