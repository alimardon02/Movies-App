package uz.gita.moviesapp.presentation.ui.screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.moviesapp.R
import uz.gita.moviesapp.databinding.ScreenHomeBinding
import uz.gita.moviesapp.presentation.ui.adapter.MoviesAdapter
import uz.gita.moviesapp.presentation.ui.adapter.PagingSource
import uz.gita.moviesapp.presentation.viewModel.HomeViewModel
import uz.gita.moviesapp.presentation.viewModel.impl.HomeViewModelImpl
import uz.gita.moviesapp.utils.extension.gone
import uz.gita.moviesapp.utils.extension.showToast
import uz.gita.moviesapp.utils.extension.visible


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home), SearchView.OnQueryTextListener {
    private val viewBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter: MoviesAdapter by lazy { MoviesAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.movieList.adapter = adapter
        viewBinding.movieList.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        adapter.setLoader {
            viewModel.getMoviesList()
        }
        //movilistda kelga datani adaoterga yuborish
        viewModel.moviesList.onEach {
            if (it.isEmpty()) viewBinding.emptyImage.visible()
            else viewBinding.emptyImage.gone()
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        //data kelish davomida progres show va hide qilish uchun
        viewModel.showLoadingFlow.onEach {
            viewBinding.progressLayout.progressLoader.isVisible = it
            viewBinding.linear.isVisible = !it
        }.launchIn(lifecycleScope)

        // internet yoq bolsa userga habar berish
        viewModel.noConnectionFlow.onEach {
            showToast("Internet bilan aloqa yo'q")
        }.launchIn(lifecycleScope)
        viewBinding.searchView.setOnQueryTextListener(this@HomeScreen)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query.let { viewModel.page = 1 }
        viewModel.getSearchList(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText.let { viewModel.page = 1 }
        viewModel.getSearchList(newText!!)
        return true
    }
}