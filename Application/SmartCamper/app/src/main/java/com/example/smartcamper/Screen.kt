package com.example.smartcamper

sealed class Screen(val route :String){
    object Welcome: Screen(route = "welcome_path")
    object LogIn: Screen(route = "login_path")

}
