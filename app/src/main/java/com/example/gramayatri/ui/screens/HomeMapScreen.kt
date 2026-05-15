package com.example.gramayatri.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gramayatri.data.models.BusLocationModel
import com.example.gramayatri.viewmodel.PassengerDashboardViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun HomeMapScreen(
    viewModel: PassengerDashboardViewModel = viewModel()
) {
    val busLocations by viewModel.busLocations.collectAsState()

    val defaultLocation = LatLng(15.2689, 76.3909)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 10f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            isMyLocationEnabled = false,
            mapStyleOptions = null // Could add industrial dark style here
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = true,
            myLocationButtonEnabled = false
        )
    ) {
        busLocations.forEach { bus ->
            AnimatedBusMarker(bus)
        }

        // Draw active routes for industrial feel
        Polyline(
            points = listOf(
                LatLng(15.2689, 76.3909),
                LatLng(15.2200, 76.5200),
                LatLng(15.1800, 76.7000),
                LatLng(15.1394, 76.9214)
            ),
            color = Color(0xFF2563EB).copy(alpha = 0.6f),
            width = 10f
        )
    }
}

@Composable
fun AnimatedBusMarker(bus: BusLocationModel) {
    val animatedLat by animateFloatAsState(
        targetValue = bus.latitude.toFloat(),
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "lat"
    )
    val animatedLng by animateFloatAsState(
        targetValue = bus.longitude.toFloat(),
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "lng"
    )

    Marker(
        state = MarkerState(position = LatLng(animatedLat.toDouble(), animatedLng.toDouble())),
        title = bus.busNumber,
        snippet = "Realtime GPS Active",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
    )
}
