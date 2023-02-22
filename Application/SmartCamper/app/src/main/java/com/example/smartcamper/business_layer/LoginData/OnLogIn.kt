package com.example.smartcamper.business_layer.LoginData

interface OnLogIn {
    fun onSuccess()
    fun onError( error:String)
}