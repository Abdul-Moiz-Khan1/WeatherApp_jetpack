package com.example.jetpack_weatherapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {
    private const val baseUrl = "https://api.weatherapi.com"

    private fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val weatherapi:weatherApi = getInstance().create(weatherApi::class.java)
}