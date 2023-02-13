package com.example.smartcamper.business_layer

import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class LoginDataImplementation {
    fun login( username:String, password:String, onLogIn: OnLogIn){
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
                if (!response.isSuccessful){
                    onLogIn.onError(response.toString())

                }else{
                    val gson = Gson()
                    val responseBody = response.body!!.toString()
                    val tokens: Map<String, String> = gson.fromJson(
                        responseBody,
                        object : TypeToken<Map<String, String>>() {}.type
                    )
                    for(token in tokens){
                        Log.e("token", token.value)
                    }

                    onLogIn.onSuccess()
                    Log.i("Response", response.body!!.string())
                }

            }
        }
        catch(error:Error) {
            error.printStackTrace()

        }
    }
}