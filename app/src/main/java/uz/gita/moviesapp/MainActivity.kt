package uz.gita.moviesapp

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.moviesapp.utils.ConnectivityReceiver
import uz.gita.moviesapp.utils.Open

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main),
    ConnectivityReceiver.ConnectivityReceiverListener {
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            Open.message.value = "Internet bilan aloqa yo'q"
        }
        else {
            Open.openScreen.value = Unit
        }
    }

    private fun registerConnectivityReceiver() {
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerConnectivityReceiver()
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
}