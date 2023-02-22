package com.example.smartcamper.ui_layer.Controls

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.smartcamper.business_layer.FetchControls.FetchControlsState

class ControlsViewModel(val fetchControlsState: FetchControlsState) : ViewModel(){
    var lightState : Boolean by mutableStateOf(false)
    var fanState : Boolean by mutableStateOf(false)
    var condState : Boolean by mutableStateOf(false)
    var coffeeState : Boolean by mutableStateOf(false)
    val controlsList =  mutableListOf(lightState, fanState, condState, coffeeState)

    fun getActivityContext(activity: Activity) {
        fetchControlsState.getActivityContext(activity)
    }

    fun changePinState(pin:Int){
        controlsList[pin] = fetchControlsState.setControlState(enabled = !controlsList[pin], pin = pin)
        Log.e("pin State ", controlsList[pin].toString())
        Log.e("light State ", lightState.toString())
    }

    fun getLastValues(){
        fetchControlsState.getControlState()
    }
}