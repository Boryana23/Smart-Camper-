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
import com.example.smartcamper.Screen

@Composable
fun LogInScreen(navController: NavController, viewModel: LoginViewModel){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
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
                viewModel.errorCheckEmail()
                viewModel.enableButton()
            },
            label = { viewModel.emailError?.let { Text(text = it) } },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.primary,
                errorBorderColor = Color.Red
            ),
            isError = viewModel.emailErrorCheck,

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
                viewModel.errorCheckPassword()
                viewModel.enableButton()
            },
            label = { viewModel.passwordError?.let { Text(text = it) } },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.primary,
                errorBorderColor = Color.Red
            ),
            isError = viewModel.passwordErrorCheck,
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
            onClick = { /*Login*/ }, modifier = Modifier
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