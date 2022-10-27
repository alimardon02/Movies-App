package uz.gita.moviesapp.utils.extension

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.time.Duration


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), message, duration)
}

fun <T : ViewBinding> T.myApply(block: T.() -> Unit) {
    block(this)
}



