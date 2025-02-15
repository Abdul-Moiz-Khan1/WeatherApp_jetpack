package com.example.jetpack_weatherapp.Api

data class weatherModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)