package com.example.smartcamper.ui_layer

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp

@Composable
fun NavBar(){
    TopAppBar(
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        navigationIcon = { Icon(imageVector = Icons.Filled.Dangerous, contentDescription = "Camper Navigation")

        },
        title = { Text(text = "Smart camper", modifier = Modifier.padding(start = 0.dp)) },
        actions = {
            IconButton(onClick = {  }) {

            }
        },

        )
}