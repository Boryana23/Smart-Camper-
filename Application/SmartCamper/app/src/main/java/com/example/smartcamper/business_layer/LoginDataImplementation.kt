package com.example.smartcamper.business_layer

import android.os.StrictMode
import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class LoginDataImplementation {
    fun login( username:String, password:String){
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var client:OkHttpClient = OkHttpClient()
        val url:String = "http://zaimov.eu:8181/api/auth/login"

        try {
            var jsonObject:JSONObject = JSONObject()
            jsonObject.put("username", username)
            jsonObject.put("password", password)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val requestBody = jsonObject.toString().toRequestBody(mediaType)

            val request:Request = Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .post(requestBody)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                Log.e("Response", response.body!!.string())
            }
        }
        catch(error:Error) {
            error.printStackTrace()

        }
    }
}