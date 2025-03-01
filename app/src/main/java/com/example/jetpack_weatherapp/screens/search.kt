package com.example.jetpack_weatherapp.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.jetpack_weatherapp.Api.networkResponse
import com.example.jetpack_weatherapp.Api.weatherModel
import com.example.jetpack_weatherapp.roomDB.ResponseDatabase
import com.example.jetpack_weatherapp.roomDB.SavedResponse
import com.example.jetpack_weatherapp.viewModel.weatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.rememberCoroutineScope
import moiz.dev.jetpack_weatherIQ.R

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun search(viewModel: weatherViewModel) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var dbResult by remember {
        mutableStateOf<SavedResponse?>(null)
    }

    val dbInstance = Room.databaseBuilder(
        context.applicationContext, ResponseDatabase::class.java, "weather_database"
    ).build()

    coroutineScope.launch {
        val cacheResult = withContext(Dispatchers.IO) {
            dbInstance.dao().getCacheModelById(0)
        }
        dbResult = cacheResult
    }


    var city by remember {
        mutableStateOf("")
    }
    var visible by remember {
        mutableIntStateOf(0)
    }

    val weatherResult = viewModel.weatherResponse.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value = city, onValueChange = {
                city = it
            }, label = {
                Text(text = "Search", color = Color.White)
            }, textStyle = TextStyle(color = Color.White), // Set text color to white
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White
                ), singleLine = true, maxLines = 1
            )
            IconButton(onClick = {
                if (city.isEmpty() || city.isBlank()) {
                    Toast.makeText(context, "Please enter city name to search", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.getData(city)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )

            }
        }
        when (val result = weatherResult.value) {

            is networkResponse.Error -> {
                visible = 1
                Log.d("where ", "error")
                dbResult?.let { OfflineWeatherScreen(it, viewModel) }
            }

            networkResponse.Loading -> {
                visible = 1
                Log.d("where ", "loading")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center // Centers the content inside the Box
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp) // No need for fillMaxSize()
                    )
                }

            }

            is networkResponse.Success -> {
                visible = 1
                Log.d("where ", "suvvess")
                saveData(result.data, dbInstance)
                WeatherScreen(result.data, dbInstance)
                Log.d("whatthis??", weatherResult.toString())
            }

            null -> {}
        }

        if (visible == 0 && (dbResult?.city?.isNotEmpty() == true)) {
            OfflineWeatherScreen(dbResult!!, viewModel = viewModel)
        } else {
            noLocation()
        }
    }

}

@Composable
fun noLocation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.nolocationxml), null, modifier = Modifier.size(180.dp)
        )
        Text(
            text = "Aww Snap! I dont have your Location\nPlease Search Location",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }

}

fun saveData(data: weatherModel, dbInstance: ResponseDatabase) {
    val response = SavedResponse(
        0,
        rain = data.forecast.forecastday[0].day.daily_chance_of_rain,
        humidity = data.current.humidity,
        windSpeed = data.current.wind_kph.toInt(),

        lastUpdated = data.current.last_updated,
        city = data.location.name,
        currentCondition = data.current.condition.text,
        currentConditionPic = data.current.condition.text,
        currentTemp = data.current.temp_c.toInt(),
        currentHighTemp = data.forecast.forecastday[0].day.maxtemp_c.toInt(),
        currentLowTemp = data.forecast.forecastday[0].day.mintemp_c.toInt(),

        //per hour temp
        data.forecast.forecastday[0].hour[0].temp_c.toInt(),
        data.forecast.forecastday[0].hour[1].temp_c.toInt(),
        data.forecast.forecastday[0].hour[2].temp_c.toInt(),
        data.forecast.forecastday[0].hour[3].temp_c.toInt(),
        data.forecast.forecastday[0].hour[4].temp_c.toInt(),
        data.forecast.forecastday[0].hour[5].temp_c.toInt(),
        data.forecast.forecastday[0].hour[6].temp_c.toInt(),
        data.forecast.forecastday[0].hour[7].temp_c.toInt(),
        data.forecast.forecastday[0].hour[8].temp_c.toInt(),
        data.forecast.forecastday[0].hour[9].temp_c.toInt(),
        data.forecast.forecastday[0].hour[10].temp_c.toInt(),
        data.forecast.forecastday[0].hour[11].temp_c.toInt(),
        data.forecast.forecastday[0].hour[12].temp_c.toInt(),
        data.forecast.forecastday[0].hour[13].temp_c.toInt(),
        data.forecast.forecastday[0].hour[14].temp_c.toInt(),
        data.forecast.forecastday[0].hour[15].temp_c.toInt(),
        data.forecast.forecastday[0].hour[16].temp_c.toInt(),
        data.forecast.forecastday[0].hour[17].temp_c.toInt(),
        data.forecast.forecastday[0].hour[18].temp_c.toInt(),
        data.forecast.forecastday[0].hour[19].temp_c.toInt(),
        data.forecast.forecastday[0].hour[20].temp_c.toInt(),
        data.forecast.forecastday[0].hour[21].temp_c.toInt(),
        data.forecast.forecastday[0].hour[22].temp_c.toInt(),
        data.forecast.forecastday[0].hour[23].temp_c.toInt(),

        //week days
        getDayFromDate(data.location.localtime.dropLast(6)),
        getDayFromDate(data.forecast.forecastday[1].date),
        getDayFromDate(data.forecast.forecastday[2].date),
        getDayFromDate(data.forecast.forecastday[3].date),
        getDayFromDate(data.forecast.forecastday[4].date),
        getDayFromDate(data.forecast.forecastday[5].date),
        getDayFromDate(data.forecast.forecastday[6].date),

        //weekly weather condition
        data.forecast.forecastday[0].day.condition.text,
        data.forecast.forecastday[1].day.condition.text,
        data.forecast.forecastday[2].day.condition.text,
        data.forecast.forecastday[3].day.condition.text,
        data.forecast.forecastday[4].day.condition.text,
        data.forecast.forecastday[5].day.condition.text,
        data.forecast.forecastday[6].day.condition.text,

        //weekly weather condition pic
        data.forecast.forecastday[0].day.condition.text,
        data.forecast.forecastday[1].day.condition.text,
        data.forecast.forecastday[2].day.condition.text,
        data.forecast.forecastday[3].day.condition.text,
        data.forecast.forecastday[4].day.condition.text,
        data.forecast.forecastday[5].day.condition.text,
        data.forecast.forecastday[6].day.condition.text,

        //low temp in week
        data.forecast.forecastday[0].day.mintemp_c.toInt(),
        data.forecast.forecastday[1].day.mintemp_c.toInt(),
        data.forecast.forecastday[2].day.mintemp_c.toInt(),
        data.forecast.forecastday[3].day.mintemp_c.toInt(),
        data.forecast.forecastday[4].day.mintemp_c.toInt(),
        data.forecast.forecastday[5].day.mintemp_c.toInt(),
        data.forecast.forecastday[6].day.mintemp_c.toInt(),

        //max temp in week
        data.forecast.forecastday[0].day.maxtemp_c.toInt(),
        data.forecast.forecastday[1].day.maxtemp_c.toInt(),
        data.forecast.forecastday[2].day.maxtemp_c.toInt(),
        data.forecast.forecastday[3].day.maxtemp_c.toInt(),
        data.forecast.forecastday[4].day.maxtemp_c.toInt(),
        data.forecast.forecastday[5].day.maxtemp_c.toInt(),
        data.forecast.forecastday[6].day.maxtemp_c.toInt(),
    )
    CoroutineScope(Dispatchers.IO).launch {
        dbInstance.dao().saveResponse(response)
    }
}



