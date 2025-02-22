package com.example.jetpack_weatherapp.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpack_weatherapp.Api.Current

@Entity(tableName = "response_table")
data class SavedResponse(
    @PrimaryKey val id: Int = 0,

    val rain:Int,
    val humidity:Int,
    val windSpeed:Int,

    val lastUpdated:String,
    val city:String,
    val currentCondition:String,
    val currentConditionPic:String,
    val currentTemp:Int,
    val currentHighTemp:Int,
    val currentLowTemp:Int,

    val hour1Temp:Int,
    val hour2Temp:Int,
    val hour3Temp:Int,
    val hour4Temp:Int,
    val hour5Temp:Int,
    val hour6Temp:Int,
    val hour7Temp:Int,
    val hour8Temp:Int,
    val hour9Temp:Int,
    val hour10Temp:Int,
    val hour11Temp:Int,
    val hour12Temp:Int,
    val hour13Temp:Int,
    val hour14Temp:Int,
    val hour15Temp:Int,
    val hour16Temp:Int,
    val hour17Temp:Int,
    val hour18Temp:Int,
    val hour19Temp:Int,
    val hour20Temp:Int,
    val hour21Temp:Int,
    val hour22Temp:Int,
    val hour23Temp:Int,
    val hour24Temp:Int,

    val day1:String,
    val day2:String,
    val day3:String,
    val day4:String,
    val day5:String,
    val day6:String,
    val day7:String,

    val weeklyCondition1:String,
    val weeklyCondition2:String,
    val weeklyCondition3:String,
    val weeklyCondition4:String,
    val weeklyCondition5:String,
    val weeklyCondition6:String,
    val weeklyCondition7:String,

    val weeklyConditionPic1:String,
    val weeklyConditionPic2:String,
    val weeklyConditionPic3:String,
    val weeklyConditionPic4:String,
    val weeklyConditionPic5:String,
    val weeklyConditionPic6:String,
    val weeklyConditionPic7:String,

    val weeklyLowTemp1:Int,
    val weeklyLowTemp2:Int,
    val weeklyLowTemp3:Int,
    val weeklyLowTemp4:Int,
    val weeklyLowTemp5:Int,
    val weeklyLowTemp6:Int,
    val weeklyLowTemp7:Int,

    val weeklyMaxTemp1:Int,
    val weeklyMaxTemp2:Int,
    val weeklyMaxTemp3:Int,
    val weeklyMaxTemp4:Int,
    val weeklyMaxTemp5:Int,
    val weeklyMaxTemp6:Int,
    val weeklyMaxTemp7:Int,
)
