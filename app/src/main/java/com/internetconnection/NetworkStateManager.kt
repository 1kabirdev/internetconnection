package com.internetconnection

import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkStateManager {

    fun setNetworkConnectivityStatus(connectivityStatus: Boolean) {
        if (Looper.myLooper() == Looper.getMainLooper()) activeNetworkStatusMLD.setValue(
            connectivityStatus
        )
        else activeNetworkStatusMLD.postValue(connectivityStatus)

    }

    val networkConnectivityStatus: LiveData<Boolean>
        get() {
            return activeNetworkStatusMLD
        }

    companion object {
        private var INSTANCE: NetworkStateManager? = null
        private val activeNetworkStatusMLD = MutableLiveData<Boolean>()

        val instance: NetworkStateManager
            get() {
                if (INSTANCE == null) {
                    INSTANCE = NetworkStateManager()
                }
                return INSTANCE!!
            }
    }
}