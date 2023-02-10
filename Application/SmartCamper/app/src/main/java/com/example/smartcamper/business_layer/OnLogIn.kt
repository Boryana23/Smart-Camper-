package com.example.smartcamper.business_layer

interface OnLogIn {
    fun onSuccess()
    fun onError( error:String)
}