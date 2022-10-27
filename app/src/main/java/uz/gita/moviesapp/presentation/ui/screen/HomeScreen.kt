package uz.gita.moviesapp.presentation.ui.screen

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.moviesapp.R
import uz.gita.moviesapp.databinding.ScreenHomeBinding


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val viewBinding by viewBinding(ScreenHomeBinding::bind)
}