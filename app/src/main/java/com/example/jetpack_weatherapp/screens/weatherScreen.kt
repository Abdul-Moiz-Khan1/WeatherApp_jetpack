package com.example.jetpack_weatherapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.util.Locale
import com.example.jetpack_weatherapp.Api.weatherModel
import com.example.jetpack_weatherapp.FutureModel
import com.example.jetpack_weatherapp.HourlyModel
import com.example.jetpack_weatherapp.R
import com.example.jetpack_weatherapp.roomDB.ResponseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@Composable
fun WeatherScreen(networkResponse: weatherModel, dbInstance: ResponseDatabase) {



    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault()) // "EEEE" gives full day name
    val day = dateFormat.format(calendar.time)
    val data = networkResponse
    val Hourlyitems = listOf(
        HourlyModel(
            data.forecast.forecastday[1].hour[0].time.drop(10),
            data.forecast.forecastday[0].hour[0].temp_c.toInt(),
            data.forecast.forecastday[1].hour[0].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[1].time.drop(10),
            data.forecast.forecastday[0].hour[1].temp_c.toInt(),
            data.forecast.forecastday[1].hour[1].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[2].time.drop(10),
            data.forecast.forecastday[0].hour[2].temp_c.toInt(),
            data.forecast.forecastday[1].hour[2].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[3].time.drop(10),
            data.forecast.forecastday[0].hour[3].temp_c.toInt(),
            data.forecast.forecastday[1].hour[3].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[4].time.drop(10),
            data.forecast.forecastday[0].hour[4].temp_c.toInt(),
            data.forecast.forecastday[1].hour[4].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[5].time.drop(10),
            data.forecast.forecastday[0].hour[5].temp_c.toInt(),
            data.forecast.forecastday[1].hour[5].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[6].time.drop(10),
            data.forecast.forecastday[0].hour[6].temp_c.toInt(),
            data.forecast.forecastday[1].hour[6].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[7].time.drop(10),
            data.forecast.forecastday[0].hour[7].temp_c.toInt(),
            data.forecast.forecastday[1].hour[7].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[8].time.drop(10),
            data.forecast.forecastday[0].hour[8].temp_c.toInt(),
            data.forecast.forecastday[1].hour[8].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[9].time.drop(10),
            data.forecast.forecastday[0].hour[9].temp_c.toInt(),
            data.forecast.forecastday[1].hour[9].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[10].time.drop(10),
            data.forecast.forecastday[0].hour[10].temp_c.toInt(),
            data.forecast.forecastday[1].hour[10].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[11].time.drop(10),
            data.forecast.forecastday[0].hour[11].temp_c.toInt(),
            data.forecast.forecastday[1].hour[11].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[12].time.drop(10),
            data.forecast.forecastday[0].hour[12].temp_c.toInt(),
            data.forecast.forecastday[1].hour[12].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[13].time.drop(10),
            data.forecast.forecastday[0].hour[13].temp_c.toInt(),
            data.forecast.forecastday[1].hour[13].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[14].time.drop(10),
            data.forecast.forecastday[0].hour[14].temp_c.toInt(),
            data.forecast.forecastday[1].hour[14].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[15].time.drop(10),
            data.forecast.forecastday[0].hour[15].temp_c.toInt(),
            data.forecast.forecastday[1].hour[15].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[16].time.drop(10),
            data.forecast.forecastday[0].hour[16].temp_c.toInt(),
            data.forecast.forecastday[1].hour[16].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[17].time.drop(10),
            data.forecast.forecastday[0].hour[17].temp_c.toInt(),
            data.forecast.forecastday[1].hour[17].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[18].time.drop(10),
            data.forecast.forecastday[0].hour[18].temp_c.toInt(),
            data.forecast.forecastday[1].hour[18].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[19].time.drop(10),
            data.forecast.forecastday[0].hour[19].temp_c.toInt(),
            data.forecast.forecastday[1].hour[19].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[20].time.drop(10),
            data.forecast.forecastday[0].hour[20].temp_c.toInt(),
            data.forecast.forecastday[1].hour[20].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[21].time.drop(10),
            data.forecast.forecastday[0].hour[21].temp_c.toInt(),
            data.forecast.forecastday[1].hour[21].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[22].time.drop(10),
            data.forecast.forecastday[0].hour[22].temp_c.toInt(),
            data.forecast.forecastday[1].hour[22].condition.icon
        ),
        HourlyModel(
            data.forecast.forecastday[1].hour[23].time.drop(10),
            data.forecast.forecastday[0].hour[23].temp_c.toInt(),
            data.forecast.forecastday[1].hour[23].condition.icon
        ),

        )
    val Futureitems = listOf(
        FutureModel(
            getDayFromDate(data.location.localtime.dropLast(6)),
            data.current.condition.text,
            data.current.condition.text,
            data.forecast.forecastday[0].day.maxtemp_c.toInt(),
            data.forecast.forecastday[0].day.mintemp_c.toInt()
        ),
        FutureModel(
            getDayFromDate(data.forecast.forecastday[1].date),
            data.forecast.forecastday[1].day.condition.text,
            data.forecast.forecastday[1].day.condition.text,
            data.forecast.forecastday[1].day.maxtemp_c.toInt(),
            data.forecast.forecastday[1].day.mintemp_c.toInt()
        ),
        FutureModel(
            getDayFromDate(data.forecast.forecastday[2].date),
            data.forecast.forecastday[2].day.condition.text,
            data.forecast.forecastday[2].day.condition.text,
            data.forecast.forecastday[2].day.maxtemp_c.toInt(),
            data.forecast.forecastday[2].day.mintemp_c.toInt()
        ),
        FutureModel(
            getDayFromDate(data.forecast.forecastday[3].date),
            data.forecast.forecastday[3].day.condition.text,
            data.forecast.forecastday[3].day.condition.text,
            data.forecast.forecastday[3].day.maxtemp_c.toInt(),
            data.forecast.forecastday[3].day.mintemp_c.toInt()
        ),
        FutureModel(
            getDayFromDate(data.forecast.forecastday[4].date),
            data.forecast.forecastday[4].day.condition.text,
            data.forecast.forecastday[4].day.condition.text,
            data.forecast.forecastday[4].day.maxtemp_c.toInt(),
            data.forecast.forecastday[4].day.mintemp_c.toInt()
        ),
        FutureModel(
            getDayFromDate(data.forecast.forecastday[5].date),
            data.forecast.forecastday[5].day.condition.text,
            data.forecast.forecastday[5].day.condition.text,
            data.forecast.forecastday[5].day.maxtemp_c.toInt(),
            data.forecast.forecastday[5].day.mintemp_c.toInt()
        ),
        FutureModel(
            getDayFromDate(data.forecast.forecastday[6].date),
            data.forecast.forecastday[6].day.condition.text,
            data.forecast.forecastday[6].day.condition.text,
            data.forecast.forecastday[6].day.maxtemp_c.toInt(),
            data.forecast.forecastday[6].day.mintemp_c.toInt()
        ),

        )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = data.location.name,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top =12.dp),
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = data.current.condition.text,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp),
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painterResource(id = getImage(data.current.condition.text)),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                        modifier = Modifier
                            .size(140.dp)
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = "$day | ${data.location.localtime}".dropLast(6),
                        fontSize = 19.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(

                        text = "${data.current.temp_c.toInt()}°C",
                        fontSize = 63.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "High:${data.forecast.forecastday[0].day.maxtemp_c.toInt()}°c | Low:${data.forecast.forecastday[0].day.mintemp_c.toInt()}°c",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(android.graphics.Color.parseColor("#643d67")),
                                        Color(
                                            android.graphics.Color.parseColor("#331866"),
                                        )
                                    )
                                ),
                                shape = RoundedCornerShape(25.dp),

                                )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            WeatherDetailsItem(
                                icon = R.drawable.rain,
                                value = "${data.forecast.forecastday[0].day.daily_chance_of_rain}%",
                                label = "Rain"
                            )
                            WeatherDetailsItem(
                                icon = R.drawable.wind,
                                value = "${data.current.wind_kph.toInt()}km/h",
                                label = "wind"
                            )
                            WeatherDetailsItem(
                                icon = R.drawable.humidity,
                                value = "${data.current.humidity}%",
                                label = "humidity"
                            )
                        }
                    }
                    Text(
                        text = "Today",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 4.dp)
                    )
                }
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(Hourlyitems) { item ->
                            DailyModelViewHolder(model = item)
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Future",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Next 7 days",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                items(Futureitems) {
                    FutureItemsView(items = it)
                }
            }
        }
    }
}


@Composable
fun FutureItemsView(items: FutureModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = items.day.take(3),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(
                id = getImage(items.picPath)
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 24.dp)
                .size(45.dp)
        )
        Text(
            text = items.status,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = "${items.highTemp}°c",
            modifier = Modifier.padding(end = 16.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            text = "${items.lowTemp}°c",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }

}

@Composable
fun DailyModelViewHolder(model: HourlyModel) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .wrapContentHeight()
            .padding(4.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#643d67")),
                        Color(
                            android.graphics.Color.parseColor("#331866"),
                        )
                    )
                ),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = model.hour,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), textAlign = TextAlign.Center
        )
        if(model.picPath == "sunny"){
            AsyncImage(
                model = R.drawable.sunny,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp),
                contentScale = ContentScale.FillBounds
            )
        }else{
            AsyncImage(
                model = "https:${model.picPath}",
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp),
                contentScale = ContentScale.FillBounds
            )

        }

        Text(
            text = "${model.temp.toString()}°c",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), textAlign = TextAlign.Center
        )
    }
}

@Composable
fun WeatherDetailsItem(icon: Int, value: String, label: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(34.dp)
        )
        Text(
            text = label,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = value,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }

}

fun getImage(condition: String): Int {

    when (condition) {
        "Clear" -> return R.drawable.sunny
        "Sunny" -> return R.drawable.sunny
        "Partly cloudy" -> return R.drawable.cloudy_sunny
        "Cloudy" -> return R.drawable.cloudy
        "Overcast" -> return R.drawable.cloudy_sunny
        "Mist" -> return R.drawable.cloudy
        "Patchy rain possible" -> return R.drawable.rainy
        "Patchy snow possible" -> return R.drawable.snowy
        "Patchy sleet possible" -> return R.drawable.snowy
        "Patchy freezing drizzle possible" -> return R.drawable.snowy
        "Thundery outbreaks possible" -> return R.drawable.storm
        "Blowing snow" -> return R.drawable.snowy
        "Blizzard" -> return R.drawable.snowy
        "Fog" -> return R.drawable.cloudy
        "Freezing fog" -> return R.drawable.cloudy
        "Patchy light drizzle" -> return R.drawable.rainy
        "Light drizzle" -> return R.drawable.rainy
        "Freezing drizzle" -> return R.drawable.rainy
        "Heavy freezing drizzle" -> return R.drawable.rainy
        "Patchy light rain" -> return R.drawable.rainy
        "Light rain" -> return R.drawable.rainy
        "Moderate rain at times" -> return R.drawable.rainy
        "Moderate rain" -> return R.drawable.rainy
        "Heavy rain at times" -> return R.drawable.rainy
        "Heavy rain" -> return R.drawable.rainy
        "Light freezing rain" -> return R.drawable.rainy
        "Moderate or heavy freezing rain" -> return R.drawable.rainy
        "Light sleet" -> return R.drawable.snowy
        "Moderate or heavy sleet" -> return R.drawable.snowy
        "Patchy light snow" -> return R.drawable.snowy
        "Light snow" -> return R.drawable.snowy
        "Patchy moderate snow" -> return R.drawable.snowy
        "Moderate snow" -> return R.drawable.snowy
        "Patchy heavy snow" -> return R.drawable.snowy
        "Heavy snow" -> return R.drawable.snowy
        "Ice pellets" -> return R.drawable.snowy
        "Light rain shower" -> return R.drawable.rainy
        "Moderate or heavy rain shower" -> return R.drawable.rainy
        "Torrential rain shower" -> return R.drawable.rainy
        "Light sleet showers" -> return R.drawable.snowy
        "Moderate or heavy sleet showers" -> return R.drawable.storm
        "Light snow showers" -> return R.drawable.snowy
        "Moderate or heavy snow showers" -> return R.drawable.snowy
        "Light showers of ice pellets" -> return R.drawable.snowy
        "Moderate or heavy showers of ice pellets" -> return R.drawable.storm
        "Patchy light rain with thunder" -> return R.drawable.storm
        "Moderate or heavy rain with thunder" -> return R.drawable.rainy
        "Patchy light snow with thunder" -> return R.drawable.rainy
        "Moderate or heavy snow with thunder" -> return R.drawable.rainy
        else -> return R.drawable.cloudy_sunny
    }

}

fun getDayFromDate(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Define format
    val date = dateFormat.parse(dateString) // Convert string to Date
    val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault()) // Get full day name
    return dayFormat.format(date ?: Date()) // Get day name
}
