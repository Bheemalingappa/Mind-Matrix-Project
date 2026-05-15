package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gramayatri.viewmodel.FleetMapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminFleetMapScreen(
    navController: NavController,
    viewModel: FleetMapViewModel = viewModel()
) {
    val liveBuses by viewModel.liveBuses.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(15.2689, 76.3909), 8f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fleet Monitoring", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0B1220))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0B1220))
                .padding(padding)
        ) {
            /**
             * LIVE STATUS CARD
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF172033))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Active Fleet: ${liveBuses.size}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Realtime Transport Intelligence Active",
                        color = Color(0xFFCBD5E1),
                        fontSize = 13.sp
                    )
                }
            }

            /**
             * GOOGLE MAP
             */
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                liveBuses.forEach { bus ->
                    val latitude = bus["latitude"] as? Double ?: return@forEach
                    val longitude = bus["longitude"] as? Double ?: return@forEach
                    val busNumber = bus["busNumber"]?.toString() ?: "Unknown Bus"
                    val route = bus["route"]?.toString() ?: "Unknown Route"
                    val status = bus["status"]?.toString() ?: "OFFLINE"

                    Marker(
                        state = MarkerState(position = LatLng(latitude, longitude)),
                        title = busNumber,
                        snippet = "$route • $status"
                    )
                }
            }
        }
    }
}