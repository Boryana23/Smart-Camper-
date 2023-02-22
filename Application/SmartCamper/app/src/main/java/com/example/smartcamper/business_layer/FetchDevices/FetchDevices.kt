package com.example.smartcamper.business_layer.FetchDevices

import android.app.Activity

interface FetchDevices {
    fun getAvailableDevices():List<Devices>
    fun getActivityContext(activity: Activity)

}