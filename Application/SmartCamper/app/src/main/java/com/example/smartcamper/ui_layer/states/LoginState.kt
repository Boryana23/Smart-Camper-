package com.example.smartcamper.ui_layer.states

sealed class LoginState(){
    object Loading:LoginState()
    object Success:LoginState()
    class Error(val error:String): LoginState()
}

