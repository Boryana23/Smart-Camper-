package com.example.smartcamper.ui_layer.Devices

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.smartcamper.business_layer.FetchDevices.Devices
import com.example.smartcamper.business_layer.FetchDevices.FetchDevices
import com.example.smartcamper.business_layer.FetchDevices.FetchDevicesImplementation

class DevicesViewModel(val fetchDevices: FetchDevices):ViewModel() {
    var devices:List<Devices> = mutableListOf()
    var devicesNames:MutableList<String> = mutableListOf()
    var devicesIds: MutableList<String?> = mutableListOf()

    fun fetchDevices(){
        devices = fetchDevices.getAvailableDevices()
        Log.e("Devices", devices.toString())

        for(device in devices){
            devicesNames
                .add(device.name)

            devicesIds
                .add(device.id["id"])
        }
    }

    fun getActivityContext(activity: Activity){
        fetchDevices.getActivityContext(activity)
    }
}