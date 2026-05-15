package com.example.gramayatri.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "GRAMA-YATRI",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Community Powered Rural Mobility",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}