package com.example.jetpack_weatherapp

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack_weatherapp.Screens.WeatherScreen
import com.example.jetpack_weatherapp.Screens.search
import com.example.jetpack_weatherapp.ui.theme.Jetpack_weatherAppTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val weatherviewmodel = ViewModelProvider(this)[weatherViewModel::class.java]

        setContent {
            Jetpack_weatherAppTheme {
                Column(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(android.graphics.Color.parseColor("#59469D")),
                                    Color(
                                        android.graphics.Color.parseColor("#643d67"),
                                    )
                                )

                            )
                        )
                ) {
                    search(weatherviewmodel)
                    WeatherScreen()
                }
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )
                window.decorView.systemUiVisibility =
                    window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR


            }
        }
    }
}