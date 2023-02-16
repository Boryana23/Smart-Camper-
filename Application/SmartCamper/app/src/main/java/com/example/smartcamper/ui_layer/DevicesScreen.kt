package com.example.smartcamper.ui_layer

import android.app.Activity
import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

@Composable
fun DevicesScreen(viewModel: DevicesViewModel, navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val activity = LocalContext.current as Activity
        viewModel.getActivityContext(activity)
        NavBar()
        viewModel.fetchDevices()


        Text(

            text = "Your devices... ",
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 50.dp)

        )

        LazyColumn {
            item { DevicesGrid(viewModel, navController)}
        }
    }

}
@Composable
fun RowScope.DeviceCell(
    text: String,
    weight: Float,
    type: String,
    icon: ImageVector?

    ) {
    if (type == "icon") {
        if (icon != null) {
            Icon(imageVector = icon, modifier = Modifier.padding(end = 20.dp).size(50.dp), contentDescription = "Camper icon")
        }
    }
    if (type == "text") {
        Text(
            text = text,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(weight)

        )
    }
}


@Composable
fun DevicesGrid(viewModel:DevicesViewModel, navController: NavController) {
    val nameColumnWeight = .7f
    val iconColumnWeight = .3f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        viewModel.devicesNames.forEachIndexed {index, name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clickable { navController.navigate(Screen.Telemetry.route + "/${viewModel.devicesIds[index]}") },
                verticalAlignment = Alignment.CenterVertically,

                ) {
                DeviceCell(
                    text = name,
                    weight = nameColumnWeight,
                    "text",
                    null
                )
                DeviceCell(
                    text = "icon",
                    weight = iconColumnWeight,
                    "icon",
                    Icons.Filled.Train
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