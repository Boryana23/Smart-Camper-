package com.example.smartcamper.business_layer.FetchControls
import android.app.Activity
import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.example.smartcamper.business_layer.FetchDevices.Devices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class FetchControlsImplementation : FetchControlsState{
    lateinit var activity: Activity


    override fun getActivityContext(activity: Activity) {
        this.activity = activity
        Log.e("ACTIVITY", this.activity.toString())
    }

    override fun getControlState(): Controls {
        var token:String? = ""
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var client: OkHttpClient = OkHttpClient()
        val url: String = "http://zaimov.eu:8181/api/plugins/rpc/twoway/04821c10-7c5a-11ed-89e5-8f9e9abd2970"
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = ""
        if (sharedPref != null) {
            token = sharedPref.getString("token", defaultValue)
        }
        try {
            var jsonObject: JSONObject = JSONObject()
            jsonObject.put("method","getGpioStatus")
            var switchData = JSONObject()
            jsonObject.put("params", switchData)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val requestBody = jsonObject.toString().toRequestBody(mediaType)

            val request: Request = Request.Builder()
                .url(url)
                .addHeader("X-Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .post(requestBody)
                .build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.e("Relay Get error: ", response.body!!.string())
                }
                else{
                    val result = response.body!!.string()
                    var newResult = result.replace("\\\"", "\"")
                    newResult = newResult.replace("\"1\"", "\"one\"")
                    newResult = newResult.replace("\"2\"", "\"two\"")
                    newResult = newResult.replace("\"3\"", "\"three\"")
                    newResult = newResult.replace("\"0\"", "\"zero\"")

                    Log.e("NEW JSON", newResult)
                    val controlsJSON = Json.decodeFromString<Controls>(newResult.subSequence(1, newResult.length-1) as String)
                    Log.e("Result ", controlsJSON.toString())
                    return controlsJSON
                }
            }
        }catch(error:Error){
            error.printStackTrace()
        }
        return Controls(2,2,2,2)
    }

    override fun setControlState(enabled: Boolean, pin: Int): Boolean{
        Log.e("Enabled ", enabled.toString())
        var result = true
        var token:String? = ""
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var client: OkHttpClient = OkHttpClient()
        val url: String = "http://zaimov.eu:8181/api/plugins/rpc/twoway/04821c10-7c5a-11ed-89e5-8f9e9abd2970"
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = ""
        if (sharedPref != null) {
            token = sharedPref.getString("token", defaultValue)
        }

        try {
            var jsonObject: JSONObject = JSONObject()
            jsonObject.put("method","setGpioStatus")
            var switchData = JSONObject()
            switchData.put("pin", pin)
            switchData.put("enabled", enabled)
            jsonObject.put("params", switchData)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val requestBody = jsonObject.toString().toRequestBody(mediaType)

            val request: Request = Request.Builder()
                .url(url)
                .addHeader("X-Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .post(requestBody)
                .build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.e("Relay error: ", response.body!!.string())
                }
                else{
                    val resultFromResponse = response.body!!.string().toBoolean()
                    Log.e("Result R", resultFromResponse.toString())
                    result = resultFromResponse
                }
            }
        }catch(error:Error){
            error.printStackTrace()
        }
        Log.e("Result", result.toString())
        return result
    }
}