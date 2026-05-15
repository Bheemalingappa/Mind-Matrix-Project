package com.example.gramayatri.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramayatri.data.models.BusModel
import com.example.gramayatri.data.models.RouteModel
import com.example.gramayatri.ui.components.BusCard
import com.example.gramayatri.ui.components.RouteCard

@Composable
fun TripBottomSheet() {

    val buses = listOf(

        BusModel(
            busNumber = "KA-34-B-1021",
            route = "Hospete → Ballari",
            eta = "8 min",
            occupancy = "42 Seats",
            speed = "45 km/h"
        ),

        BusModel(
            busNumber = "KA-22-F-8812",
            route = "Koppal → Hospete",
            eta = "14 min",
            occupancy = "18 Seats",
            speed = "52 km/h"
        )
    )

    val routes = listOf(

        RouteModel(
            source = "Hospete",
            destination = "Ballari",
            duration = "48 min",
            distance = "31 km",
            fare = "₹65",
            trafficLevel = "Moderate"
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 32.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xF2172033)
        )
    ) {

        Column(
            modifier = Modifier.padding(22.dp)
        ) {

            Text(
                text = "Nearby Buses",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Row(
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                )
            ) {

                buses.forEach {

                    BusCard(
                        bus = it,
                        onBookSeat = { }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Recommended Route",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            routes.forEach {

                RouteCard(
                    route = it,
                    onRouteClick = { }
                )
            }
        }
    }
}