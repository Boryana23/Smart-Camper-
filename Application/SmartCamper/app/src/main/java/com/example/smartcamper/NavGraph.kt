package com.example.smartcamper

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartcamper.ui_layer.WelcomeScreen

@Composable
fun NavGraph(navHost: NavHostController) {
    NavHost(
        navController = navHost,
        startDestination = Screen.Welcome.route
    ){
        composable( route = Screen.Welcome.route){
            WelcomeScreen(navController = navHost)
        }
    }
}
