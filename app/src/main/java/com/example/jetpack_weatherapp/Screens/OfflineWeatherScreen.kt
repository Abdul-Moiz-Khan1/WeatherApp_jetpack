package com.example.jetpack_weatherapp.Screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.RoomDatabase
import coil.compose.AsyncImage
import com.example.jetpack_weatherapp.Api.weatherModel
import com.example.jetpack_weatherapp.FutureModel
import com.example.jetpack_weatherapp.HourlyModel
import com.example.jetpack_weatherapp.R
import com.example.jetpack_weatherapp.roomDB.ResponseDatabase
import com.example.jetpack_weatherapp.roomDB.SavedResponse
import com.example.jetpack_weatherapp.viewModel.weatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OfflineWeatherScreen(dbInstance: ResponseDatabase, viewModel: weatherViewModel) {

    val context = LocalContext.current
    var result by remember {
        mutableStateOf<SavedResponse?>(null)
    }

    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        val cacheResult = withContext(Dispatchers.IO) {
            dbInstance.dao().getCacheModelById(0)
        }
        result = cacheResult
    }

    if (result == null) {
        CircularProgressIndicator()
    } else {

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault()) // "EEEE" gives full day name
        val day = dateFormat.format(calendar.time)
        val Hourlyitems = listOf(
            HourlyModel(
                "00:00",
                result!!.hour1Temp,
                "sunny",
            ),
            HourlyModel(
                "01:00",
                result!!.hour2Temp,
                "sunny"
            ),
            HourlyModel(
                "02:00",
                result!!.hour3Temp,
                "sunny"
            ),
            HourlyModel(
                "03:00",
                result!!.hour4Temp,
                "sunny"
            ),

            HourlyModel(
                "04:00",
                result!!.hour5Temp,
                "sunny"
            ),
            HourlyModel(
                "05:00",
                result!!.hour6Temp,
                "sunny"
            ),
            HourlyModel(
                "06:00",
                result!!.hour7Temp,
                "sunny"
            ),
            HourlyModel(
                "07:00",
                result!!.hour8Temp,
                "sunny"
            ),

            HourlyModel(
                "08:00",
                result!!.hour9Temp,
                "sunny"
            ),
            HourlyModel(
                "09:00",
                result!!.hour10Temp,
                "sunny"
            ),
            HourlyModel(
                "10:00",
                result!!.hour11Temp,
                "sunny"
            ),
            HourlyModel(
                "11:00",
                result!!.hour12Temp,
                "sunny"
            ),

            HourlyModel(
                "12:00",
                result!!.hour13Temp,
                "sunny"
            ),
            HourlyModel(
                "13:00",
                result!!.hour14Temp,
                "sunny"
            ),
            HourlyModel(
                "14:00",
                result!!.hour15Temp,
                "sunny"
            ),
            HourlyModel(
                "15:00",
                result!!.hour16Temp,
                "sunny"
            ),

            HourlyModel(
                "16:00",
                result!!.hour17Temp,
                "sunny"
            ),
            HourlyModel(
                "17:00",
                result!!.hour18Temp,
                "sunny"
            ),
            HourlyModel(
                "18:00",
                result!!.hour19Temp,
                "sunny"
            ),
            HourlyModel(
                "19:00",
                result!!.hour20Temp,
                "sunny"
            ),
            HourlyModel(
                "20:00",
                result!!.hour21Temp,
                "sunny"
            ),
            HourlyModel(
                "21:00",
                result!!.hour22Temp,
                "sunny"
            ),
            HourlyModel(
                "22:00",
                result!!.hour23Temp,
                "sunny"
            ),
            HourlyModel(
                "23:00",
                result!!.hour24Temp,
                "sunny"
            ),

            )
        val Futureitems = listOf(
            FutureModel(
                result!!.day1,
                result!!.weeklyCondition1,
                result!!.weeklyConditionPic1,
                result!!.weeklyMaxTemp1,
                result!!.weeklyLowTemp1,

                ),
            FutureModel(
                result!!.day2,
                result!!.weeklyCondition2,
                result!!.weeklyConditionPic2,
                result!!.weeklyMaxTemp2,
                result!!.weeklyLowTemp2,

                ),
            FutureModel(
                result!!.day3,
                result!!.weeklyCondition3,
                result!!.weeklyConditionPic3,
                result!!.weeklyMaxTemp3,
                result!!.weeklyLowTemp3,

                ),
            FutureModel(
                result!!.day4,
                result!!.weeklyCondition4,
                result!!.weeklyConditionPic4,
                result!!.weeklyMaxTemp4,
                result!!.weeklyLowTemp4,

                ),
            FutureModel(
                result!!.day5,
                result!!.weeklyCondition5,
                result!!.weeklyConditionPic5,
                result!!.weeklyMaxTemp5,
                result!!.weeklyLowTemp5,

                ),
            FutureModel(
                result!!.day6,
                result!!.weeklyCondition6,
                result!!.weeklyConditionPic6,
                result!!.weeklyMaxTemp6,
                result!!.weeklyLowTemp6,

                ),
            FutureModel(
                result!!.day7,
                result!!.weeklyCondition7,
                result!!.weeklyConditionPic7,
                result!!.weeklyMaxTemp7,
                result!!.weeklyLowTemp7,

                ),


            )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            )
        {
            Text(
                text = "Last Updated: ${result!!.lastUpdated.drop(5).replace("-", "/")}",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 2.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
            )
            IconButton(onClick = {
                viewModel.getData(result!!.city)
            }) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )

            }

        }

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
                            text = result!!.city,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 2.dp),
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = result!!.currentCondition,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 12.dp),
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painterResource(id = getImage(result!!.currentConditionPic)),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                            modifier = Modifier
                                .size(140.dp)
                                .padding(top = 8.dp)
                        )
                        Text(
                            text = result!!.day1,
                            fontSize = 19.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(

                            text = "${result!!.currentTemp}°C",
                            fontSize = 63.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "High:${result!!.currentHighTemp}°c | Low:${result!!.currentLowTemp}°c",
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
                                    value = "${result!!.rain}%",
                                    label = "Rain"
                                )
                                WeatherDetailsItem(
                                    icon = R.drawable.wind,
                                    value = "${result!!.windSpeed}km/h",
                                    label = "wind"
                                )
                                WeatherDetailsItem(
                                    icon = R.drawable.humidity,
                                    value = "${result!!.humidity}%",
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
}