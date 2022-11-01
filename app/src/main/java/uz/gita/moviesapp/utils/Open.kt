package uz.gita.moviesapp.utils

import androidx.lifecycle.MutableLiveData

object Open {
    val openScreen = MutableLiveData<Unit>()
    val message = MutableLiveData<String>()
}