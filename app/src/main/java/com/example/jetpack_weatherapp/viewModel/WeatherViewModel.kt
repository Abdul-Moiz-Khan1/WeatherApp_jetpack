package com.example.jetpack_weatherapp.viewModel

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.jetpack_weatherapp.Api.networkResponse
import com.example.jetpack_weatherapp.Api.retrofitInstance
import com.example.jetpack_weatherapp.Api.weatherModel
import com.example.jetpack_weatherapp.Constants
import com.example.jetpack_weatherapp.roomDB.ResponseDatabase
import kotlinx.coroutines.launch


class weatherViewModel : ViewModel() {

    private val weatherapi = retrofitInstance.weatherapi
    private val _weatherResponse = MutableLiveData<networkResponse<weatherModel>>()
    val weatherResponse: LiveData<networkResponse<weatherModel>> = _weatherResponse
    var lat = ""
    var long = ""


    fun getData(city: String) {
        _weatherResponse.value = networkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherapi.getWeather(Constants.apiKey, city, 7)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResponse.value = networkResponse.Success(it)
                        Log.d("reponsed", _weatherResponse.value.toString())
                    }
                } else {
                    _weatherResponse.value = networkResponse.Error("failed to load Data")
                }
            } catch (e: Exception) {
                _weatherResponse.value = networkResponse.Error("failed to load Data")
                Log.e("Network Error", e.printStackTrace().toString())
            }
        }

    }
}