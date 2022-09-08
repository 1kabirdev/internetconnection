package com.internetconnection

import android.app.Application
import android.util.Log

class App : Application() {

    private val TAG: String = App::class.java.simpleName
    private lateinit var mNetworkMonitoringUtil: NetworkMonitoringUtil

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() called")

        mNetworkMonitoringUtil = NetworkMonitoringUtil(applicationContext)
        mNetworkMonitoringUtil.checkNetworkState()
        mNetworkMonitoringUtil.registerNetworkCallbackEvents()
    }
}