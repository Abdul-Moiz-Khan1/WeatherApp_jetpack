package com.example.jetpack_weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_weatherapp.Api.networkResponse
import com.example.jetpack_weatherapp.Api.retrofitInstance
import com.example.jetpack_weatherapp.Api.weatherModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class weatherViewModel : ViewModel() {

    private val weatherapi = retrofitInstance.weatherapi
    private val _weatherResponse = MutableLiveData<networkResponse<weatherModel>>()
    val weatherResponse:LiveData<networkResponse<weatherModel>> = _weatherResponse

    fun getData(city: String) {
        _weatherResponse.value = networkResponse.Loading
        viewModelScope.launch {
        try{
            val response = weatherapi.getWeather(Constants.apiKey, city)
            if(response.isSuccessful){
                response.body()?.let {
                    _weatherResponse.value = networkResponse.Success(it)
                }
            }
            else{
                _weatherResponse.value = networkResponse.Error("failed to load Data")
            }
        }catch (e:Exception){
            _weatherResponse.value = networkResponse.Error("failed to load Data")
            Log.e("Network Error" , e.printStackTrace().toString())
        }
        }

    }
}