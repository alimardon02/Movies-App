package uz.gita.moviesapp.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.moviesapp.R
import uz.gita.moviesapp.databinding.ScreenSplashBinding
import uz.gita.moviesapp.presentation.viewModel.SplashViewModel
import uz.gita.moviesapp.presentation.viewModel.impl.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewBinding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}