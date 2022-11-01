package uz.gita.moviesapp.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.moviesapp.R
import uz.gita.moviesapp.databinding.ScreenHomeBinding
import uz.gita.moviesapp.presentation.ui.adapter.MoviesAdapter
import uz.gita.moviesapp.presentation.viewModel.HomeViewModel
import uz.gita.moviesapp.presentation.viewModel.impl.HomeViewModelImpl
import uz.gita.moviesapp.utils.Open
import uz.gita.moviesapp.utils.extension.hideKeyboard
import uz.gita.moviesapp.utils.extension.showToast


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home), SearchView.OnQueryTextListener {
    private val viewBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val viewModels by viewModels<HomeViewModelImpl>()
    private val adapter: MoviesAdapter by lazy { MoviesAdapter() }


    @SuppressLint("DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        movieList.adapter = adapter
        movieList.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        Open.message.observe(viewLifecycleOwner, mesaageObserver)

        //movilistda kelga datani adaoterga yuborish
        viewModels.flowSearch.onEach {
            adapter.submitData(it)
        }.launchIn(lifecycleScope)

        //data kelish davomida progres show va hide qilish uchun
        viewModel.showLoadingFlow.onEach {
            progressLayout.progressLoader.isVisible = it
        }.launchIn(lifecycleScope)

        // internet yoq bolsa userga habar berish
        viewModel.noConnectionFlow.onEach {
            showToast("Internet bilan aloqa yo'q")
        }.launchIn(lifecycleScope)
        searchView.setOnQueryTextListener(this@HomeScreen)
        searchView.setOnCloseListener {
            hideKeyboard()
            searchView.clearFocus()
            return@setOnCloseListener false
        }


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.getSearchList(query!!)
        submitData()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.getSearchList(newText!!)
        submitData()
        return true
    }

    private fun submitData() {
        viewModels.flowSearch.onEach {
            adapter.submitData(it)
        }.launchIn(lifecycleScope)
    }

    private val mesaageObserver = Observer<String> {
        showToast(it)
    }
}