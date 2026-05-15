package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {

    val background = Brush.verticalGradient(

        colors = listOf(
            Color(0xFF020617),
            Color(0xFF071120)
        )
    )

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(18.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "Realtime Mobility",

            color = Color.White,

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        DashboardCard(
            title = "Nearby Buses",
            value = "18 Active",
            iconColor = Color(0xFF3B82F6)
        )

        Spacer(modifier = Modifier.height(16.dp))

        DashboardCard(
            title = "Smart Routes",
            value = "6 Optimized",
            iconColor = Color(0xFF10B981)
        )

        Spacer(modifier = Modifier.height(16.dp))

        DashboardCard(
            title = "Live Stations",
            value = "42 Connected",
            iconColor = Color(0xFFF59E0B)
        )
    }
}

@Composable
fun DashboardCard(

    title: String,

    value: String,

    iconColor: Color
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(

            containerColor = Color(0xFF14213D)
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(

                    text = title,

                    color = Color.Gray,

                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(

                    text = value,

                    color = Color.White,

                    fontSize = 24.sp,

                    fontWeight = FontWeight.Bold
                )
            }

            Icon(

                imageVector = Icons.Default.DirectionsBus,

                contentDescription = null,

                tint = iconColor,

                modifier = Modifier.size(42.dp)
            )
        }
    }
}