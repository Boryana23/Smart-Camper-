package com.example.smartcamper.ui_layer

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
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

@Composable
fun TelemetryScreen(viewModel:TelemetryViewModel, id:String){
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
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp)

        )

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
            Icon(imageVector = icon, contentDescription = "Camper icon")
        }
    }
    if (type == "text") {
        Text(
            text = text,
            fontSize = 20.sp,
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
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 15.dp)
                .weight(weight)
        )
    }
}

@Composable
fun TelemetryGrid(viewModel:TelemetryViewModel) {
    val nameColumnWeight = .4f
    val iconColumnWeight = .2f
    val valueColumnWeight = .4f


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        viewModel.TelemetryNames.forEach {name ->
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
                    text = "icon",
                    weight = nameColumnWeight,
                    type = "text",
                    icon = null
                )

                TelemetryCell(
                    text = "icon",
                    weight = valueColumnWeight,
                    type = "value",
                    icon = null
                )


            }
            Divider(
                color = Color.Blue,
                thickness = 2.dp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}