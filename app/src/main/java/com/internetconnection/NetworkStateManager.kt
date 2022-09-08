package com.internetconnection

import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkStateManager {

    fun setNetworkConnectivityStatus(connectivityStatus: Boolean) {
        Log.d(
            TAG,
            "setNetworkConnectivityStatus() called with: connectivityStatus = [$connectivityStatus]"
        )
        if (Looper.myLooper() == Looper.getMainLooper()) {
            activeNetworkStatusMLD.setValue(connectivityStatus)
        } else {
            activeNetworkStatusMLD.postValue(connectivityStatus)
        }
    }

    val networkConnectivityStatus: LiveData<Boolean>
        get() {
            Log.d(TAG, "getNetworkConnectivityStatus() called")
            return activeNetworkStatusMLD
        }

    companion object {
        val TAG: String = NetworkStateManager::class.java.simpleName
        private var INSTANCE: NetworkStateManager? = null
        private val activeNetworkStatusMLD = MutableLiveData<Boolean>()

        val instance: NetworkStateManager
            get() {
                if (INSTANCE == null) {
                    Log.d(TAG, "getInstance() called: Creating new instance")
                    INSTANCE = NetworkStateManager()
                }
                return INSTANCE!!
            }
    }
}