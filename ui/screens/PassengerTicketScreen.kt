package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PassengerTicketScreen() {

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF020617))
            .padding(20.dp),

        contentAlignment = Alignment.Center
    ) {

        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(28.dp),

            colors = CardDefaults.cardColors(

                containerColor = Color(0xFF14213D)
            )
        ) {

            Column(

                modifier = Modifier.padding(24.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                /**
                 * HEADER
                 */
                Icon(

                    imageVector =
                        Icons.Default.DirectionsBus,

                    contentDescription = null,

                    tint = Color(0xFF2563EB),

                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(

                    text = "Booking Confirmed",

                    color = Color.White,

                    fontWeight = FontWeight.Bold,

                    fontSize = 26.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(

                    text = "Digital Travel Ticket",

                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Divider(
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(20.dp))

                /**
                 * BUS INFO
                 */
                TicketRow(
                    title = "Bus Number",
                    value = "KA-34-B-1021"
                )

                TicketRow(
                    title = "Route",
                    value = "Hospete → Ballari"
                )

                TicketRow(
                    title = "Seat",
                    value = "A12"
                )

                TicketRow(
                    title = "Fare",
                    value = "₹45"
                )

                TicketRow(
                    title = "Booking ID",
                    value = "GY-284729"
                )

                Spacer(modifier = Modifier.height(28.dp))

                /**
                 * QR PLACEHOLDER
                 */
                Box(

                    modifier = Modifier
                        .size(160.dp)
                        .background(
                            Color.White,
                            RoundedCornerShape(16.dp)
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Text(

                        text = "QR",

                        color = Color.Black,

                        style =
                            MaterialTheme.typography
                                .headlineLarge
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(

                    text =
                        "Show this ticket while boarding",

                    color = Color.LightGray,

                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun TicketRow(

    title: String,

    value: String
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(

            text = title,

            color = Color.LightGray
        )

        Text(

            text = value,

            color = Color.White,

            fontWeight = FontWeight.Bold
        )
    }
}