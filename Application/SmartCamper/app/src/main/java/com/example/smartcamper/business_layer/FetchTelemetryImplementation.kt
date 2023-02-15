package com.example.smartcamper.business_layer

import android.app.Activity
import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class FetchTelemetryImplementation {
    lateinit var activity: Activity
    var deviceId = ""
    var telemetryNames:MutableList<String> = mutableListOf()

    fun fetchTelemetryNames(){
        var token:String? = ""
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var client: OkHttpClient = OkHttpClient()
        val url: String = "http://zaimov.eu:8181/api/plugins/telemetry/DEVICE/" + deviceId + "/keys/timeseries"
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = ""
        if (sharedPref != null) {
            token = sharedPref.getString("token", defaultValue)
        }

        try {
            val request: Request = Request.Builder()
                .url(url)
                .addHeader("X-Authorization", "Bearer " + token )
                .build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.e("Response error", response.toString())
                } else {
                    Log.e("Response tel:", response.body!!.string())

                }
            }
        } catch (error: Error) {
            error.printStackTrace()
        }

    }

    fun getDeviceId(id:String){
        this.deviceId = id

    }

    fun getActivityContext(activity: Activity){
        this.activity = activity
    }
}