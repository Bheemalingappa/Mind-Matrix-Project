
package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LandingScreen(

    onPassengerClick: () -> Unit = {},
    onConductorClick: () -> Unit = {},
    onAdminClick: () -> Unit = {}

) {

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0B1120),
            Color(0xFF111827),
            Color(0xFF172033)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "GRAMA-YATRI",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Rural Mobility Intelligence Platform",
            color = Color(0xFFCBD5E1),
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Community Powered Realtime Transit Tracking",
            color = Color(0xFF94A3B8),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(42.dp))

        RoleCard(
            title = "Passenger",
            subtitle = "Track buses, ETA and live alerts",
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            },
            buttonText = "Continue as Passenger",
            buttonColor = Color(0xFF4F46E5),
            onClick = onPassengerClick
        )

        Spacer(modifier = Modifier.height(20.dp))

        RoleCard(
            title = "Conductor Console",
            subtitle = "Operational reporting and journey control",
            icon = {
                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            },
            buttonText = "Continue as Conductor",
            buttonColor = Color(0xFF0F766E),
            onClick = onConductorClick
        )

        Spacer(modifier = Modifier.height(26.dp))

        TextButton(
            onClick = onAdminClick
        ) {

            Icon(
                imageVector = Icons.Default.AdminPanelSettings,
                contentDescription = null,
                tint = Color(0xFFF59E0B)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Admin Control Center",
                color = Color(0xFFF59E0B),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun RoleCard(

    title: String,
    subtitle: String,
    icon: @Composable () -> Unit,
    buttonText: String,
    buttonColor: Color,
    onClick: () -> Unit

) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E293B)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        buttonColor,
                        shape = RoundedCornerShape(18.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                icon()
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subtitle,
                color = Color(0xFFCBD5E1),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                )
            ) {

                Text(
                    text = buttonText,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}