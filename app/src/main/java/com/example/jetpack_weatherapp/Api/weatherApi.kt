package com.example.jetpack_weatherapp.Api


import retrofit2.http.GET
import retrofit2.http.Query

interface weatherApi {

    @GET("/v1/forecast.json")
    suspend fun getWeather(
        @Query("key") apiKey:String,
        @Query("q") city:String,
        @Query("days") day:Int
    ): retrofit2.Response<weatherModel>
}