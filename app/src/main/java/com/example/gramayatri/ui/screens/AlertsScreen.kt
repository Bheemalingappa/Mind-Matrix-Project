package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gramayatri.data.Alert
import com.example.gramayatri.viewmodel.AlertsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AlertsScreen(
    viewModel: AlertsViewModel = viewModel()
) {
    val alerts by viewModel.alerts.collectAsState()

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
            text = "Realtime Alerts",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(alerts) { alert ->
                IndustrialAlertCard(alert = alert)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun IndustrialAlertCard(alert: Alert) {
    val dateFormat = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    val dateString = dateFormat.format(Date(alert.timestamp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF14213D)
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = alert.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = alert.message,
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Priority : ${alert.type}",
                            color = when (alert.type) {
                                "High", "SOS", "Emergency" -> Color.Red
                                "Moderate", "Delay" -> Color(0xFFF59E0B)
                                else -> Color(0xFF10B981)
                            },
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = dateString,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color(0xFF3B82F6),
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}
