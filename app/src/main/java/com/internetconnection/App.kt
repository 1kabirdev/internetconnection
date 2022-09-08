package com.internetconnection

import android.app.Application

class App : Application() {

    private lateinit var mNetworkMonitoringUtil: NetworkMonitoringUtil

    override fun onCreate() {
        super.onCreate()
        mNetworkMonitoringUtil = NetworkMonitoringUtil(applicationContext)
        mNetworkMonitoringUtil.checkNetworkState()
        mNetworkMonitoringUtil.registerNetworkCallbackEvents()
    }
}