package com.example.smartcamper.ui_layer.Controls

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartcamper.business_layer.FetchControls.Controls
import com.example.smartcamper.business_layer.FetchControls.FetchControlsState
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class ControlsViewModel(val fetchControlsState: FetchControlsState) : ViewModel(){
    var lightState by Delegates.notNull<Boolean>()
    var fanState : Boolean by mutableStateOf(false)
    var condState : Boolean by mutableStateOf(false)
    var coffeeState : Boolean by mutableStateOf(false)

    var areControlsFetched by mutableStateOf(0)

    lateinit var previousState : Controls

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
    }

    fun changePinState(pin:Int){
        val controlsList =  mutableListOf(lightState, fanState, condState, coffeeState)
        controlsList[pin] = fetchControlsState.setControlState(enabled = !controlsList[pin], pin = pin)
        Log.e("pin State ", controlsList[pin].toString())
        Log.e("light State ", lightState.toString())
    }

    private fun getControlsValues(){
        previousState = fetchControlsState.getControlState()

        if(previousState.zero < 2){
            lightState = convertIntToBoolean(previousState.zero)
            Log.e("LIGHT ST", lightState.toString())
        }

        if(previousState.one < 2){
            fanState = convertIntToBoolean(previousState.one)
        }

        if(previousState.two < 2){
            condState = convertIntToBoolean(previousState.two)
        }

        if(previousState.three < 2){
            coffeeState = convertIntToBoolean(previousState.three)
        }
    }
    private fun convertIntToBoolean(num: Int): Boolean{
        return num > 0
    }
}