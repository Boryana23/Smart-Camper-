package com.example.smartcamper.ui_layer

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartcamper.Screen
import com.example.smartcamper.ui_layer.Telemetry.TelemetryViewModel

@Composable
fun TelemetryScreen(navController:NavController, viewModel: TelemetryViewModel, id:String){
    Column {
        val activity = LocalContext.current as Activity
        viewModel.fetchTelemetry(activity, id)
        NavBar()


        Text(

            text = "Vehicle telemetry.. ",
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 50.dp)

        )
        OutlinedButton(
            onClick = { navController.navigate(Screen.Controls.route) }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
            Text(
                text = "Enter controls",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp
            )
        }

        LazyColumn {
            item { TelemetryGrid(viewModel) }
        }
    }

}

@Composable
fun RowScope.TelemetryCell(
    icon: ImageVector?,
    text: String,
    weight: Float,
    type: String


) {
    if (type == "icon") {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = "Camper icon", modifier = Modifier.padding(start = 20.dp).size (50.dp))
        }
    }
    if (type == "text") {
        Text(
            text = text,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 15.dp)
                .weight(weight)
        )
    }

    if(type == "value"){
        Text(
            text = text,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = Color(0XFF03dac5),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 15.dp)
                .weight(weight)
        )
    }
}

@Composable
fun TelemetryGrid(viewModel: TelemetryViewModel) {
    val nameColumnWeight = .4f
    val iconColumnWeight = .2f
    val valueColumnWeight = .4f


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        viewModel.telemetryNames.forEach { name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {

                TelemetryCell(
                    text = "icon",
                    weight = iconColumnWeight,
                    type = "icon",
                    icon = Icons.Filled.CarRental
                )

                TelemetryCell(
                    text = name,
                    weight = nameColumnWeight,
                    type = "text",
                    icon = null
                )

                viewModel.telemetryValues[name]?.let {
                    TelemetryCell(
                        text = it.value,
                        weight = valueColumnWeight,
                        type = "value",
                        icon = null
                    )
                }


            }

            Text(
                text = "Last fetched: ${viewModel.telemetryValues[name]?.ts}",
                fontSize = 10.sp,
                textAlign = TextAlign.Left,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 10.dp)
            )

            Divider(
                color = Color.Blue,
                thickness = 2.dp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}