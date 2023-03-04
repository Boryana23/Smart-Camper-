package com.example.smartcamper.business_layer.FetchControls

import android.app.Activity

interface FetchControlsState {
    fun getActivityContext(activity: Activity)
    fun setControlState(enabled: Boolean, pin: Int) : Boolean
    fun getControlState() : Controls
}