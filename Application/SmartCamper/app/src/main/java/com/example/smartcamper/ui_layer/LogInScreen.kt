package com.example.smartcamper.ui_layer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LogInScreen(navController: NavController, viewModel: LoginViewModel){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(20.dp)) {
        Text(
            text = "Hello, camper owner... ",
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp)
        )

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = {
                viewModel.email = it
            },
           label = { Text(text = viewModel.validateEmail())  },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.primary,
                errorBorderColor = Color.Red
            ),
            isError = viewModel.isEmailInvalid,

            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, top = 10.dp),
            placeholder = { Text("Enter your email") },

            )
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = {
                viewModel.password = it
                viewModel.validatePassword()
                            },
            label = { Text(text = viewModel.validatePassword())  },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.primary,
                errorBorderColor = Color.Red
            ),
            isError = viewModel.isPasswordInvalid,
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "Password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, top = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        OutlinedButton(
            onClick = { viewModel.login() }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
            Text(
                text = "Enter",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}