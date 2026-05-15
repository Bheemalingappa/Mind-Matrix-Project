package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavController
import com.example.gramayatri.viewmodel.AdminViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAnalyticsScreen(
    navController: NavController,
    viewModel: AdminViewModel = viewModel()
) {
    val analytics by viewModel.analytics.collectAsState()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF06111F),
            Color(0xFF0B1727)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Transit Analytics", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF06111F))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AnalyticsDetailCard("Journey Completion Rate", "${(analytics.journeyCompletionRate * 100).toInt()}%", Color(0xFF059669))
            AnalyticsDetailCard("ETA Accuracy", "${(analytics.etaAccuracy * 100).toInt()}%", Color(0xFF2563EB))
            AnalyticsDetailCard("Daily Revenue", "₹${analytics.dailyRevenue}", Color(0xFFF59E0B))
            AnalyticsDetailCard("Passenger Traffic", "${analytics.totalPassengers}", Color(0xFF7C3AED))
        }
    }
}

@Composable
fun AnalyticsDetailCard(title: String, value: String, accent: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF172033))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(title, color = Color(0xFFCBD5E1), fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(value, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = 0.7f,
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = accent,
                trackColor = Color(0xFF0B1220),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )
        }
    }
}