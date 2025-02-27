package com.example.jetpack_weatherapp.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.jetpack_weatherapp.R
import com.example.jetpack_weatherapp.viewModel.weatherViewModel
import com.google.android.gms.location.*
import kotlinx.coroutines.delay

var goOffline: Boolean = false

@Composable
fun Splash(viewModel: weatherViewModel, navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash1))
    val progress by animateLottieCompositionAsState(composition)

    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }
    var locationFetched by remember { mutableStateOf(false) }

    // 🚀 Permission Request Handler (inside LaunchedEffect)
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
        if (isGranted) {
            getLocation(context) { lat, lon ->
                viewModel.lat = lat.toString()
                viewModel.long = lon.toString()
                goOffline = true
                locationFetched = true
                Log.d("Location", "Lat: ${viewModel.lat}, Lon: ${viewModel.long}")
                // Fetch weather data after getting location
                viewModel.getData("${viewModel.lat},${viewModel.long}")
            }
        }
    }

    LaunchedEffect(Unit) {
        val granted = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!granted) {
            Log.d("Location", "Not granted")
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            Log.d("Location", "granted")
            permissionGranted = true
            getLocation(context) { lat, lon ->
                viewModel.lat = lat.toString()
                viewModel.long = lon.toString()
                goOffline = true
                locationFetched = true
                Log.d("Location", "Lat: ${viewModel.lat}, Lon: ${viewModel.long}")
                viewModel.getData("${viewModel.lat},${viewModel.long}")
            }

        }
        delay(3800) // Wait for splash animation
        isVisible = false
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true } // Removes splash from backstack
        }
    }
    if (isVisible) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(350.dp)
            )
        }
    }
}

@SuppressLint("MissingPermission")
fun getLocation(context: Context, onLocationReceived: (Double, Double) -> Unit) {
    Log.d("Location", "in get location")
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) !=
        PackageManager.PERMISSION_GRANTED
    ) {
        Log.e("Location", "Permission not granted")
        return
    }
    fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let {
            Log.d("Location", "got it ${it.latitude}")
            onLocationReceived(it.latitude, it.longitude)
        }
    }.addOnFailureListener {
        Log.e("Location", "Failed to get location: ${it.message}")
    }
}
