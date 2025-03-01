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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.jetpack_weatherapp.FutureModel
import com.example.jetpack_weatherapp.HourlyModel
import com.example.jetpack_weatherapp.roomDB.SavedResponse
import com.example.jetpack_weatherapp.viewModel.weatherViewModel
import moiz.dev.jetpack_weatherIQ.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun OfflineWeatherScreen(dbResult: SavedResponse, viewModel: weatherViewModel) {

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault()) // "EEEE" gives full day name
    val day = dateFormat.format(calendar.time)
    val Hourlyitems = listOf(
        HourlyModel(
            "00:00",
            dbResult.hour1Temp,
            "sunny",
        ),
        HourlyModel(
            "01:00",
            dbResult.hour2Temp,
            "sunny"
        ),
        HourlyModel(
            "02:00",
            dbResult.hour3Temp,
            "sunny"
        ),
        HourlyModel(
            "03:00",
            dbResult.hour4Temp,
            "sunny"
        ),

        HourlyModel(
            "04:00",
            dbResult.hour5Temp,
            "sunny"
        ),
        HourlyModel(
            "05:00",
            dbResult.hour6Temp,
            "sunny"
        ),
        HourlyModel(
            "06:00",
            dbResult.hour7Temp,
            "sunny"
        ),
        HourlyModel(
            "07:00",
            dbResult.hour8Temp,
            "sunny"
        ),

        HourlyModel(
            "08:00",
            dbResult.hour9Temp,
            "sunny"
        ),
        HourlyModel(
            "09:00",
            dbResult.hour10Temp,
            "sunny"
        ),
        HourlyModel(
            "10:00",
            dbResult.hour11Temp,
            "sunny"
        ),
        HourlyModel(
            "11:00",
            dbResult.hour12Temp,
            "sunny"
        ),

        HourlyModel(
            "12:00",
            dbResult.hour13Temp,
            "sunny"
        ),
        HourlyModel(
            "13:00",
            dbResult.hour14Temp,
            "sunny"
        ),
        HourlyModel(
            "14:00",
            dbResult.hour15Temp,
            "sunny"
        ),
        HourlyModel(
            "15:00",
            dbResult.hour16Temp,
            "sunny"
        ),

        HourlyModel(
            "16:00",
            dbResult.hour17Temp,
            "sunny"
        ),
        HourlyModel(
            "17:00",
            dbResult.hour18Temp,
            "sunny"
        ),
        HourlyModel(
            "18:00",
            dbResult.hour19Temp,
            "sunny"
        ),
        HourlyModel(
            "19:00",
            dbResult.hour20Temp,
            "sunny"
        ),
        HourlyModel(
            "20:00",
            dbResult.hour21Temp,
            "sunny"
        ),
        HourlyModel(
            "21:00",
            dbResult.hour22Temp,
            "sunny"
        ),
        HourlyModel(
            "22:00",
            dbResult.hour23Temp,
            "sunny"
        ),
        HourlyModel(
            "23:00",
            dbResult.hour24Temp,
            "sunny"
        ),

        )
    val Futureitems = listOf(
        FutureModel(
            dbResult.day1,
            dbResult.weeklyCondition1,
            dbResult.weeklyConditionPic1,
            dbResult.weeklyMaxTemp1,
            dbResult.weeklyLowTemp1,

            ),
        FutureModel(
            dbResult.day2,
            dbResult.weeklyCondition2,
            dbResult.weeklyConditionPic2,
            dbResult.weeklyMaxTemp2,
            dbResult.weeklyLowTemp2,

            ),
        FutureModel(
            dbResult.day3,
            dbResult.weeklyCondition3,
            dbResult.weeklyConditionPic3,
            dbResult.weeklyMaxTemp3,
            dbResult.weeklyLowTemp3,

            ),
        FutureModel(
            dbResult.day4,
            dbResult.weeklyCondition4,
            dbResult.weeklyConditionPic4,
            dbResult.weeklyMaxTemp4,
            dbResult.weeklyLowTemp4,

            ),
        FutureModel(
            dbResult.day5,
            dbResult.weeklyCondition5,
            dbResult.weeklyConditionPic5,
            dbResult.weeklyMaxTemp5,
            dbResult.weeklyLowTemp5,

            ),
        FutureModel(
            dbResult.day6,
            dbResult.weeklyCondition6,
            dbResult.weeklyConditionPic6,
            dbResult.weeklyMaxTemp6,
            dbResult.weeklyLowTemp6,

            ),
        FutureModel(
            dbResult.day7,
            dbResult.weeklyCondition7,
            dbResult.weeklyConditionPic7,
            dbResult.weeklyMaxTemp7,
            dbResult.weeklyLowTemp7,

            ),


        )

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        )
    {
        Text(
            text = "Last Updated: ${dbResult.lastUpdated.drop(5).replace("-", "/")}",
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 2.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
        )
        IconButton(onClick = {
            viewModel.getData(dbResult.city)
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
                        text = dbResult.city,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 2.dp),
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = dbResult.currentCondition,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp),
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painterResource(id = getImage(dbResult.currentConditionPic)),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                        modifier = Modifier
                            .size(140.dp)
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = dbResult.day1,
                        fontSize = 19.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(

                        text = "${dbResult.currentTemp}°C",
                        fontSize = 63.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "High:${dbResult.currentHighTemp}°c | Low:${dbResult.currentLowTemp}°c",
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
                                value = "${dbResult.rain}%",
                                label = "Rain"
                            )
                            WeatherDetailsItem(
                                icon = R.drawable.wind,
                                value = "${dbResult.windSpeed}km/h",
                                label = "wind"
                            )
                            WeatherDetailsItem(
                                icon = R.drawable.humidity,
                                value = "${dbResult.humidity}%",
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