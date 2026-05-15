package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gramayatri.viewmodel.LiveTrackingViewModel

@Composable
fun LiveTrackingScreen(
    busNumber: String = "KA-34-B-1021",
    viewModel: LiveTrackingViewModel = viewModel()
) {
    LaunchedEffect(busNumber) {
        viewModel.trackBus(busNumber)
    }

    val busData by viewModel.busLocation.collectAsState()
    val busLocation = LatLng(
        busData?.latitude ?: 15.3350,
        busData?.longitude ?: 76.4600
    )

    val cameraPositionState = rememberCameraPositionState {

        position = CameraPosition.fromLatLngZoom(
            busLocation,
            12f
        )
    }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF020617),
            Color(0xFF071120),
            Color(0xFF111827)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),

            cameraPositionState = cameraPositionState,

            properties = MapProperties(
                isMyLocationEnabled = false
            ),

            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                compassEnabled = true,
                myLocationButtonEnabled = false
            )
        ) {

            Marker(
                state = MarkerState(
                    position = busLocation
                ),

                title = "KA-34-B-1021",

                snippet = "Hospete → Ballari"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(18.dp)
        ) {

            Card(
                shape = RoundedCornerShape(28.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xE6172033)
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = "Live Bus Tracking",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Realtime Smart Mobility Intelligence",
                        color = Color(0xFFCBD5E1),
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TrackingStatusCard(
                            title = "BUS",
                            value = "KA-34"
                        )

                        TrackingStatusCard(
                            title = "ETA",
                            value = "8 MIN"
                        )

                        TrackingStatusCard(
                            title = "STATUS",
                            value = "LIVE"
                        )
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(18.dp),

            shape = RoundedCornerShape(30.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xF2172033)
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = null,
                        tint = Color(0xFF2563EB)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {

                        Text(
                            text = "KA-34-B-1021",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Text(
                            text = "Hospete → Ballari",
                            color = Color(0xFFCBD5E1),
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    LiveInfoItem(
                        icon = Icons.Default.Speed,
                        title = "Speed",
                        value = "48 km/h"
                    )

                    LiveInfoItem(
                        icon = Icons.Default.Timeline,
                        title = "ETA",
                        value = "8 mins"
                    )
                }
            }
        }
    }
}

@Composable
fun TrackingStatusCard(
    title: String,
    value: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            color = Color(0xFF94A3B8),
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LiveInfoItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF2563EB)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {

            Text(
                text = title,
                color = Color(0xFF94A3B8),
                fontSize = 12.sp
            )

            Text(
                text = value,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}