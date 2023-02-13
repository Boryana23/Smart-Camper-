package com.example.smartcamper.ui_layer

import android.app.Activity
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Dangerous
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
fun DevicesScreen(viewModel: DevicesViewModel) {
    Column {
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
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp)

        )

        LazyColumn {
            item { DevicesGrid(viewModel)}
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
}


@Composable
fun DevicesGrid(viewModel:DevicesViewModel) {
    val nameColumnWeight = .7f
    val iconColumnWeight = .3f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        viewModel.devicesNames.forEach {name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
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
                    Icons.Filled.CarRental
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