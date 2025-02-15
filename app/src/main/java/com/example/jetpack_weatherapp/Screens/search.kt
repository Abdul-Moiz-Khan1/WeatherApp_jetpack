package com.example.jetpack_weatherapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_weatherapp.Api.networkResponse
import com.example.jetpack_weatherapp.R
import com.example.jetpack_weatherapp.weatherViewModel


@Composable
fun search(viewModel: weatherViewModel) {

    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherResponse.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
           ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField( value = city, onValueChange = {
                city = it
            },
                label = {
                    Text(text = "Search" , color = Color.White)
                })
            IconButton(onClick = {
                viewModel.getData(city)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search" , tint = Color.White)

            }
        }
        when (val result = weatherResult.value) {
            is networkResponse.Error -> {
                Text(text = result.message)
            }

            networkResponse.Loading -> {
                CircularProgressIndicator()
            }

            is networkResponse.Success -> {

                Text(text = result.data.toString())
            }

            null -> {}
        }
    }
}


