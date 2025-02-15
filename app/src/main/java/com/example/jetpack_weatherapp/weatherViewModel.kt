package com.example.jetpack_weatherapp

import android.util.Log
import androidx.lifecycle.ViewModel

class weatherViewModel: ViewModel() {

    fun getData(city:String){
        Log.d("cityname" , city)
    }
}