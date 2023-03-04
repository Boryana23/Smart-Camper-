package com.example.smartcamper

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartcamper.business_layer.FetchControls.FetchControlsImplementation
import com.example.smartcamper.business_layer.FetchDevices.FetchDevicesImplementation
import com.example.smartcamper.business_layer.FetchTelemetry.FetchTelemetryImplementation
import com.example.smartcamper.business_layer.LoginData.LoginDataImplementation
import com.example.smartcamper.ui_layer.*
import com.example.smartcamper.ui_layer.Controls.ControlsScreen
import com.example.smartcamper.ui_layer.Controls.ControlsViewModel
import com.example.smartcamper.ui_layer.Devices.DevicesViewModel
import com.example.smartcamper.ui_layer.LogIn.LoginViewModel
import com.example.smartcamper.ui_layer.Telemetry.TelemetryViewModel

@Composable
fun NavGraph(navHost: NavHostController) {
    NavHost(
        navController = navHost,
        startDestination = Screen.Devices.route
    ){
        composable( route = Screen.Welcome.route){
            WelcomeScreen(navController = navHost)
        }

        composable( route = Screen.LogIn.route){
            LogInScreen(navController = navHost, viewModel = LoginViewModel(loginData = LoginDataImplementation()))
        }

        composable(route = Screen.Devices.route){
            DevicesScreen(viewModel = DevicesViewModel(fetchDevices = FetchDevicesImplementation()), navController = navHost)
        }

        composable(
            route = Screen.Telemetry.route + "/{deviceId}"
        ) {navBackStack ->
            val id = navBackStack.arguments?.getString("deviceId")
            if (id != null) {
                TelemetryScreen(viewModel = TelemetryViewModel(fetchTelemetry = FetchTelemetryImplementation()), id = id, navController = navHost)
            }
        }

        composable( route = Screen.Controls.route){
            ControlsScreen(navController = navHost, viewModel = ControlsViewModel(fetchControlsState = FetchControlsImplementation()))
        }
    }
}
