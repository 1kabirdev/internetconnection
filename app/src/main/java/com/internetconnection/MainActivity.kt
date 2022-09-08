package com.internetconnection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.internetconnection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        NetworkStateManager.instance.networkConnectivityStatus
            .observe(this, activeNetworkStateObserver)
    }

    private val activeNetworkStateObserver: Observer<Boolean> =
        Observer<Boolean> { isConnected -> withConnection(isConnected) }

    private fun withConnection(isConnected: Boolean) =
        if (isConnected) mBinding.tvMessage.text = "Доступ к Интернету подключен"
        else mBinding.tvMessage.text = "Нет доступа к Интернету"

}