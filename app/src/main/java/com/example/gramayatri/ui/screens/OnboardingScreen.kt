package com.example.gramayatri.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(
    onContinue: () -> Unit
) {

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Welcome to Grama-Yatri",

                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text =
                    "Community Powered Rural Bus Tracking System",

                modifier = Modifier.padding(top = 12.dp)
            )

            Button(
                onClick = onContinue,

                modifier = Modifier.padding(top = 24.dp)
            ) {

                Text("Get Started")
            }
        }
    }
}