package uz.gita.moviesapp.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.gita.moviesapp.BuildConfig.API_KEY
import uz.gita.moviesapp.data.sources.common.models.Results
import uz.gita.moviesapp.data.sources.remote.api.MoviesApi
import uz.gita.moviesapp.domain.usecase.MoviesUseCase
import uz.gita.moviesapp.presentation.ui.adapter.PagingSearchSource
import uz.gita.moviesapp.presentation.ui.adapter.PagingSource
import uz.gita.moviesapp.presentation.viewModel.HomeViewModel
import uz.gita.moviesapp.utils.extension.eventValueFlow
import uz.gita.moviesapp.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
) : HomeViewModel, ViewModel() {

    override val moviesList = eventValueFlow<List<Results>>()
    override val showLoadingFlow = eventValueFlow<Boolean>()
    override val noConnectionFlow = eventValueFlow<Boolean>()
    override val showMassageFlow = eventValueFlow<String>()
    override val errorFlow = eventValueFlow<String>()
    override var page = 1
    override var totalPage = 0
    private val cachedList = ArrayList<Results>()

    init {
        getMoviesList()
    }


    override fun getMoviesList() {
        viewModelScope.launch {
            showLoadingFlow.emit(true)
            moviesUseCase.getAllMovies(API_KEY, page++).collect {
                showLoadingFlow.emit(false)
                if (!isConnected()) {
                    noConnectionFlow.emit(false)
                    return@collect
                }
                it.onSuccess { data ->
                    totalPage = data.total_page
                    cachedList.addAll(data.results)
                    moviesList.emit(cachedList)
                }.onFailure { error ->
                    errorFlow.emit(error.toString())
                }
            }
        }
    }

    override fun getSearchList(query: String) {
        viewModelScope.launch {
            page++
            moviesUseCase.getAllSearchMovies(API_KEY, query, page).collect {
                if (!isConnected()) {
                    noConnectionFlow.emit(false)
                    return@collect
                }
                it.onSuccess { data ->
                    moviesList.emit(data.results)
                }.onFailure { error ->
                    errorFlow.emit(error.toString())
                }
            }
        }
    }

    /* var flowSearch = Pager(
         PagingConfig(pageSize = 20)
     ) {
         PagingSearchSource(moviesApi, _query)
     }.flow
         .cachedIn(viewModelScope)*/

}