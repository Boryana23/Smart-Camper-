package com.example.smartcamper.business_layer.FetchTelemetry

import android.app.Activity

interface FetchTelemetry {
    fun fetchTelemetryNames()
    fun fetchTelemetryValues() : Map<String, TelemetryValues>
    fun dumpTelemetryNames():String
    fun dumpTelemetryValues(dataEntry:Map<String, List<TelemetryValues>>): Map<String, TelemetryValues>
    fun getDeviceId(id:String)
    fun getActivityContext(activity: Activity)
}