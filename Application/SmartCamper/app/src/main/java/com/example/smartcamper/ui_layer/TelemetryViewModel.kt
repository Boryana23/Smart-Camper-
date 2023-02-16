package com.example.smartcamper.ui_layer

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.smartcamper.business_layer.FetchTelemetryImplementation
import com.example.smartcamper.business_layer.TelemetryValues

class TelemetryViewModel(val fetchTelemetry: FetchTelemetryImplementation):ViewModel() {
    var telemetryNames:Set<String> = mutableSetOf()
    var telemetryValues:Map<String, TelemetryValues> = mutableMapOf()

    fun fetchTelemetry(activity: Activity, id:String){
        fetchTelemetry.getActivityContext(activity)
        fetchTelemetry.getDeviceId(id)
        telemetryValues = fetchTelemetry.fetchTelemetryValues()
        telemetryNames = telemetryValues.keys
    }
}