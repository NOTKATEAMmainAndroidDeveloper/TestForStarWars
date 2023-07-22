package com.ntk.testforstarwars

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ntk.testforstarwars.moduls.InAppSetting
import com.ntk.testforstarwars.navigation.AppNavigation
import com.ntk.testforstarwars.recievers.NetworkRecevier
import com.ntk.testforstarwars.ui.theme.GreyBlack
import com.ntk.testforstarwars.ui.theme.SetBarsColor


class MainActivity : ComponentActivity(), NetworkRecevier.ConnectivityReceiverListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InAppSetting(context = application)

        try{
            registerReceiver(NetworkRecevier(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            NetworkRecevier.connectivityReceiverListener = this
        }catch (_:java.lang.Exception){

        }

        setContent {
            SetBarsColor(window, Color.Black)

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = GreyBlack
            ) {
                AppNavigation()
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        InAppSetting.instance.IS_ONLINE.value = isConnected
    }
}