package uz.gita.moviesapp.presentation.viewModel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import uz.gita.moviesapp.data.sources.common.models.Results

interface HomeViewModel {
    val moviesList: Flow<List<Results>>
    /**
     * Api dan malumot kelguncha load qilish uchun flow data
     */
    val showLoadingFlow: Flow<Boolean>
    /**intetnet uchun */
    val noConnectionFlow: Flow<Boolean>
    /**message uchun */
    val showMassageFlow: Flow<String>
    val errorFlow: Flow<String>
    var page: Int
    var totalPage: Int

    fun getMoviesList()
    fun getSearchList(query: String)
}