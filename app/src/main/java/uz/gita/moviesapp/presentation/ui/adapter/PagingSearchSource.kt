package uz.gita.moviesapp.presentation.ui.adapter

import androidx.paging.PagingState
import androidx.paging.PagingSource
import uz.gita.moviesapp.BuildConfig.API_KEY
import uz.gita.moviesapp.BuildConfig.BASE_URL
import uz.gita.moviesapp.data.sources.common.models.Results
import uz.gita.moviesapp.data.sources.remote.api.MoviesApi
import javax.inject.Singleton


@Singleton
class PagingSearchSource(private val moviesApi: MoviesApi, private val query: String) :
    PagingSource<Int, Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = moviesApi.searchMovies(API_KEY, query, nextPageNumber)

            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (nextPageNumber == 1) null else -1,
                nextKey = if (nextPageNumber == response.body()?.total_page) null else response.body()?.page?.plus(
                    1
                )
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}