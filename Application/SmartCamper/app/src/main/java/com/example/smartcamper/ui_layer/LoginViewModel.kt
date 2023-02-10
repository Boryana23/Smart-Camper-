package com.example.smartcamper.ui_layer

import android.text.TextUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartcamper.business_layer.LoginDataImplementation
import com.example.smartcamper.ui_layer.states.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel(val loginData:LoginDataImplementation): ViewModel() {
    var isEmailInvalid: Boolean by mutableStateOf(true)
    var email : String  by mutableStateOf("")
    var password : String by mutableStateOf("")
    var isPasswordInvalid: Boolean by mutableStateOf(true)
    private val _stateFlow = MutableStateFlow<LoginState>(LoginState.Loading)//private state flow that we modify
    public val stateFlow: StateFlow<LoginState> = _stateFlow //public state flow displaying last state


    fun validateEmail():String{
        if(TextUtils.isEmpty(email)){
            isEmailInvalid = true
            return "Cannot continue without email"
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isEmailInvalid = true
            return "This is not a valid email!"
        }
        isEmailInvalid = false
        return ""
    }

    fun validatePassword():String{
        if(TextUtils.isEmpty(password)){
            isPasswordInvalid = true
            return "Cannot continue without password"
        }

        if(password.length < 5){
            isPasswordInvalid = true
            return "Length should be above 5 symbols"
        }

        isPasswordInvalid = false
        return ""
    }

    fun login(){ //starts a couroutine for the business logic
        viewModelScope.launch{
            _stateFlow.emit(LoginState.Loading)

        }

        viewModelScope.launch {
            loginData.login(username = email, password = password) }

    }

}