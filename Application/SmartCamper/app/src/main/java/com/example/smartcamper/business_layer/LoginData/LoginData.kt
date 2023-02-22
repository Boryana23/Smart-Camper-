package com.example.smartcamper.business_layer.LoginData

import android.app.Activity

interface LoginData {
    fun login( username:String, password:String, onLogIn: OnLogIn)
    fun getActivityContext(activity: Activity)
}