package uz.gita.moviesapp.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.registerReceiver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.moviesapp.R
import uz.gita.moviesapp.databinding.ScreenSplashBinding
import uz.gita.moviesapp.presentation.viewModel.SplashViewModel
import uz.gita.moviesapp.presentation.viewModel.impl.SplashViewModelImpl
import uz.gita.moviesapp.utils.ConnectivityReceiver
import uz.gita.moviesapp.utils.Open
import uz.gita.moviesapp.utils.extension.showToast

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewBinding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Open.message.observe(viewLifecycleOwner, mesaageObserver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Open.openScreen.observe(this@SplashScreen, openScreenObserver)
        viewModel.openNextScreenFlow.onEach {
            findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
        }.launchIn(lifecycleScope)
    }


    private val openScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
    }

    private val mesaageObserver = Observer<String> {
        showToast(it)
    }


}