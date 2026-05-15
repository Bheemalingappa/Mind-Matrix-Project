package com.example.gramayatri.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramayatri.data.models.BusModel

@Composable
fun PremiumETAWidget(
    bus: BusModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFF2563EB), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(bus.busNumber, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(bus.route, color = Color(0xFF94A3B8), fontSize = 14.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(bus.eta, color = Color(0xFF10B981), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("ETA", color = Color(0xFF94A3B8), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun EmergencyAssistanceCard(
    onSOSClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF450A0A))
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Warning, contentDescription = null, tint = Color(0xFFEF4444), modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Emergency Assistance", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Trigger SOS or share live trip", color = Color(0xFFF87171), fontSize = 12.sp)
            }
            Button(
                onClick = onSOSClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("SOS", fontWeight = FontWeight.Bold)
            }
        }
    }
}