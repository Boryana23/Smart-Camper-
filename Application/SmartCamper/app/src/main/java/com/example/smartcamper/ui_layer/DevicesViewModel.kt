package com.example.smartcamper.ui_layer

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.smartcamper.business_layer.FetchDevicesImplementation
import okhttp3.internal.wait

class DevicesViewModel(val fetchDevices:FetchDevicesImplementation):ViewModel() {
    var devices:Map<String, String> = mutableMapOf()
    var devicesNames:List<String> = mutableListOf()

    fun fetchDevices(){
        //devices =
        fetchDevices.getAvailableDevices()
        //devicesNames = devices.keys
    }

    fun getActivityContext(activity: Activity){
        fetchDevices.getActivityContext(activity)
    }
}