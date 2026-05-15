package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gramayatri.data.models.SavedRouteModel
import com.example.gramayatri.viewmodel.SavedRoutesViewModel

@Composable
fun RoutesScreen(
    viewModel: SavedRoutesViewModel = viewModel()
) {
    val savedRoutes by viewModel.savedRoutes.collectAsState()

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
            text = "Saved Routes",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (savedRoutes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No saved routes yet", color = Color.Gray)
            }
        } else {
            LazyColumn {
                items(savedRoutes) { route ->
                    SavedRouteCard(route = route, onDelete = { viewModel.removeRoute(route.routeId) })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun SavedRouteCard(route: SavedRouteModel, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF14213D)
        )
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${route.source} → ${route.destination}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Quick Access Route",
                    color = Color(0xFF10B981),
                    fontSize = 14.sp
                )
            }
            
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFEF4444))
            }

            Icon(
                imageVector = Icons.Default.Route,
                contentDescription = null,
                tint = Color(0xFF3B82F6),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}