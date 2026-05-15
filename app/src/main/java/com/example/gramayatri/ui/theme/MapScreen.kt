package com.example.gramayatri.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.gramayatri.data.BusPing
import com.example.gramayatri.data.FirebaseRepository
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {

    val repository = remember {
        FirebaseRepository()
    }

    val busPings = remember {
        mutableStateListOf<BusPing>()
    }

    LaunchedEffect(Unit) {

        repository.listenToBusPings(

            onDataLoaded = { buses ->

                busPings.clear()
                busPings.addAll(buses)
            },

            onError = {

            }
        )
    }

    val cameraPositionState = rememberCameraPositionState {

        position = CameraPosition.fromLatLngZoom(
            LatLng(15.1394, 76.9214),
            10f
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

        busPings.forEach { bus ->

            Marker(
                state = MarkerState(
                    position = LatLng(
                        bus.latitude,
                        bus.longitude
                    )
                ),

                title = bus.busId,

                snippet =
                    "${bus.routeName}\nETA ${bus.etaMinutes} mins"
            )
        }
    }
}