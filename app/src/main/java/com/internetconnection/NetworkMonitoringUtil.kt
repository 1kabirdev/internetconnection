package com.internetconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

class NetworkMonitoringUtil(context: Context) : NetworkCallback() {

    private val mNetworkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()
    private val mConnectivityManager: ConnectivityManager
    private val mNetworkStateManager: NetworkStateManager

    init {
        mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        mNetworkStateManager = NetworkStateManager.instance
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Log.d(TAG, "onAvailable() called: Connected to network")
        mNetworkStateManager.setNetworkConnectivityStatus(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Log.e(TAG, "onLost() called: with: Lost network connection")
        mNetworkStateManager.setNetworkConnectivityStatus(false)
    }

    fun registerNetworkCallbackEvents() {
        Log.d(TAG, "registerNetworkCallbackEvents() called")
        mConnectivityManager.registerNetworkCallback(mNetworkRequest, this)
    }

    fun checkNetworkState() {
        try {
            val networkInfo = mConnectivityManager.activeNetworkInfo
            mNetworkStateManager.setNetworkConnectivityStatus(
                networkInfo != null
                        && networkInfo.isConnected
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    companion object {
        val TAG: String = NetworkMonitoringUtil::class.java.simpleName
    }
}