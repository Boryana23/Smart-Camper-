package com.example.smartcamper.business_layer.FetchDevices

import android.app.Activity
import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class FetchDevicesImplementation:FetchDevices {

    lateinit var activity: Activity
    override fun getAvailableDevices():List<Devices> {

        var token:String? = ""
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var client: OkHttpClient = OkHttpClient()
        val url: String = "http://zaimov.eu:8181/api/tenant/devices?pageSize=100&page=0"
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

                    val gson = Gson()
                    val resp =  response.body!!.string()

                    val trimmedResp = "[" + resp.substringAfter("[").substringBeforeLast("]") + "]"

                    Log.e("RESP", trimmedResp)
                    val entryData = gson.fromJson(trimmedResp, Array<Devices>::class.java).asList()
                    Log.e("data entries", entryData.toString())
                    return entryData
                }


            }
        } catch (error: Error) {
            error.printStackTrace()
        }

        return listOf()
    }

    override fun getActivityContext(activity: Activity){
        this.activity = activity
    }
}