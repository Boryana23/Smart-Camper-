package com.example.smartcamper.ui_layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartcamper.Screen

@Composable
fun WelcomeScreen( navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Text(
            text = "Smart Camper",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp)
        )
        Image(
            painter = painterResource(id = com.example.smartcamper.R.drawable.camper_welcome),
            contentDescription = "CamperWelcome",
            modifier = Modifier
                .padding(20.dp)
                .size(350.dp)
                .fillMaxWidth()
        )

        OutlinedButton(
            onClick = { navController.navigate(Screen.LogIn.route) }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
            Text(
                text = "LogIn",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp
            )
        }

    }
}