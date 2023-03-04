package com.example.smartcamper.ui_layer.Controls

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartcamper.business_layer.FetchControls.FetchControlsState
import kotlinx.coroutines.launch

class ControlsViewModel(val fetchControlsState: FetchControlsState) : ViewModel(){
    var lightState : Boolean by mutableStateOf(false)
    var fanState : Boolean by mutableStateOf(false)
    var condState : Boolean by mutableStateOf(false)
    var coffeeState : Boolean by mutableStateOf(false)
    val controlsList =  mutableListOf(lightState, fanState, condState, coffeeState)
    var areControlsFetched by mutableStateOf(0)

    lateinit var previousState : Map<String, Int>

    fun getPreviousState(){
        if(areControlsFetched == 0){
            viewModelScope.launch {
                getControlsValues()
                areControlsFetched += 1
            }
            Log.e("are controls fetched: ", areControlsFetched.toString())
        }
    }

    fun getActivityContext(activity: Activity) {
        fetchControlsState.getActivityContext(activity)
        getPreviousState()
    }

    fun changePinState(pin:Int){
        controlsList[pin] = fetchControlsState.setControlState(enabled = !controlsList[pin], pin = pin)
        Log.e("pin State ", controlsList[pin].toString())
        Log.e("light State ", lightState.toString())
    }

    fun getControlsValues(){
        previousState = fetchControlsState.getControlState()
        for(i in previousState){
            if (i.key.toInt() == 0){
                lightState = convertIntToBoolean(i.value)
            }

            if (i.key.toInt() == 1){
                fanState = convertIntToBoolean(i.value)
            }

            if (i.key.toInt() == 2){
                condState = convertIntToBoolean(i.value)
            }

            if (i.key.toInt() == 3){
                coffeeState = convertIntToBoolean(i.value)
            }
        }
    }
    private fun convertIntToBoolean(num: Int): Boolean{
        return num > 0
    }
}