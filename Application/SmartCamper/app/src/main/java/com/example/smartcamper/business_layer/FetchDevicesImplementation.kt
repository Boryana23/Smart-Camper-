package com.example.smartcamper.business_layer

import android.app.Activity
import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

class FetchDevicesImplementation {

    lateinit var activity: Activity
    fun getAvailableDevices() {
        val names: MutableList<String> = mutableListOf()
        val ids: MutableList<String> = mutableListOf()
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
                    val dataEntries: Map<String, String> = gson.fromJson(
                        response.body!!.string(),
                        object : TypeToken<Map<String, String>>() {}.type
                    )
                    Log.e("data entries", dataEntries.toString())
                }




            }
        } catch (error: Error) {
            error.printStackTrace()
        }
    }

    fun getActivityContext(activity: Activity){
        this.activity = activity


    }
}