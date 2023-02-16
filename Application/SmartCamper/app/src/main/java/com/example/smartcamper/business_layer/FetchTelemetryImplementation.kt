package com.example.smartcamper.business_layer

import android.app.Activity
import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
                    //Log.e("Response tel:", response.body!!.string())
                    val gson = Gson()
                    val resp =  response.body!!.string()

                    telemetryNames = gson.fromJson(resp, object : TypeToken<List<String>>() {}.type)
                    Log.e("telemetry entries", telemetryNames.toString())
                }
            }
        } catch (error: Error) {
            error.printStackTrace()
        }

    }

    fun fetchTelemetryValues(){
        var token:String? = ""
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var client: OkHttpClient = OkHttpClient()
        val url: String = "http://zaimov.eu:8181/api/plugins/telemetry/DEVICE/" +
                deviceId +
                "/values/timeseries?keys=" + dumpTelemetryNames()
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
                    //Log.e("Response values:", response.body!!.string())
                    val gson = Gson()
                    val resp =  response.body!!.string()

                    val dataEntry:Map<String, List<TelemetryValues>> = gson.fromJson(resp, object : TypeToken<Map<String, List<TelemetryValues>>>() {}.type)
                    Log.e("telemetry data entries", dataEntry.toString())
                }
            }
        } catch (error: Error) {
            error.printStackTrace()
        }

    }

    fun dumpTelemetryNames():String {
        var result = ""
        for(entry in telemetryNames){
            result += entry
            result+=","
        }
        return result
    }

    fun getDeviceId(id:String){
        this.deviceId = id

    }

    fun getActivityContext(activity: Activity){
        this.activity = activity
    }
}

