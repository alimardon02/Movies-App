package uz.gita.moviesapp.presentation.viewModel

import kotlinx.coroutines.flow.Flow


interface SplashViewModel {
    /**
     * Api dan malumot kelguncha load qilish uchun flow data
     */
    val showLoadingFlow: Flow<Boolean>
    /**intetnet uchun */
    val noConnectionFlow: Flow<Boolean>
    /** Keyingi oynaga o'tish uchun flow data */
    val openNextScreenFlow: Flow<Unit>
    val messageFlow:Flow<String>
}