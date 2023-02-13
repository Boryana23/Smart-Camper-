package com.example.smartcamper.ui_layer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DevicesScreen(){
    Text(
        text = "Your devices... ",
        fontSize = 40.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        color = Color.Blue,
        modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp)

    )
}