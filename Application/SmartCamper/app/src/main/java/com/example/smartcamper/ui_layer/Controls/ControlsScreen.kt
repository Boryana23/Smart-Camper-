package com.example.smartcamper.ui_layer.Controls

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartcamper.ui_layer.DevicesGrid
import com.example.smartcamper.ui_layer.NavBar

@Composable
fun ControlsScreen(viewModel: ControlsViewModel, navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val activity = LocalContext.current as Activity
        viewModel.getActivityContext(activity)
        NavBar()

        viewModel.getControlsValues()

        Text(

            text = "Control your appliances:  ",
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 50.dp)

        )

        LazyColumn {
            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        imageVector = Icons.Filled.Lightbulb,

                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(50.dp),
                        contentDescription = "Camper icon"
                    )
                    val checkedLightState = remember { mutableStateOf(false) }
                    Switch(
                        checked = checkedLightState.value,
                        onCheckedChange = {
                            checkedLightState.value = it
                            viewModel.lightState = !checkedLightState.value
                            viewModel.changePinState(pin = 0)
                        }
                    )
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        imageVector = Icons.Filled.Air,

                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(50.dp),
                        contentDescription = "Camper icon"
                    )
                    val checkedFanState = remember { mutableStateOf(false) }
                    Switch(
                        checked = checkedFanState.value,
                        onCheckedChange = {
                            checkedFanState.value = it
                            viewModel.fanState = !checkedFanState.value
                            viewModel.changePinState(pin = 0)
                        }
                    )
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        imageVector = Icons.Filled.WbCloudy,

                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(50.dp),
                        contentDescription = "Camper icon"
                    )
                    val checkedCondState = remember { mutableStateOf(false) }
                    Switch(
                        checked = checkedCondState.value,
                        onCheckedChange = {
                            checkedCondState.value = it
                            viewModel.condState = !checkedCondState.value
                            viewModel.changePinState(pin = 0)
                        }
                    )
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        imageVector = Icons.Filled.CoffeeMaker,

                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(50.dp),
                        contentDescription = "Camper icon"
                    )
                    val checkedCoffeeState = remember { mutableStateOf(false) }
                    Switch(
                        checked = checkedCoffeeState.value,
                        onCheckedChange = {
                            checkedCoffeeState.value = it
                            viewModel.coffeeState = !checkedCoffeeState.value
                            viewModel.changePinState(pin = 0)
                        }
                    )
                }
            }
        }
    }
}

