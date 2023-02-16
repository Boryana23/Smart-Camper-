package com.example.smartcamper.ui_layer

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.smartcamper.business_layer.FetchTelemetryImplementation

class TelemetryViewModel(val fetchTelemetry: FetchTelemetryImplementation):ViewModel() {
    var TelemetryNames:MutableList<String> = mutableListOf()
    fun fetchTelemetry(activity: Activity, id:String){
        fetchTelemetry.getActivityContext(activity)
        fetchTelemetry.getDeviceId(id)
        fetchTelemetry.fetchTelemetryNames()
        fetchTelemetry.fetchTelemetryValues()
    }
}