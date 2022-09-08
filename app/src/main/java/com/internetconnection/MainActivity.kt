package com.internetconnection

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.internetconnection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        NetworkStateManager.instance.networkConnectivityStatus
            .observe(this, activeNetworkStateObserver)
    }

    private val activeNetworkStateObserver: Observer<Boolean> =
        Observer<Boolean> { isConnected -> prepareLottieAnimation(isConnected) }

    private fun prepareLottieAnimation(isConnected: Boolean) {
        Log.d(TAG, "prepareLottieAnimation() called with: isConnected = [$isConnected]")
        if (isConnected) {
            mBinding.tvMessage.text = "Доступ к Интернету подключен"
        } else {
            mBinding.tvMessage.text = "Нет доступа к Интернету"
        }
    }
}