package com.example.gramayatri.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gramayatri.data.BusPing
import com.example.gramayatri.data.FirebaseRepository

@Composable
fun LiveBusScreen() {

    val context = LocalContext.current

    var busId by remember { mutableStateOf("") }
    var driverName by remember { mutableStateOf("") }
    var routeName by remember { mutableStateOf("") }
    var currentStop by remember { mutableStateOf("") }
    var etaMinutes by remember { mutableStateOf("") }

    val repository = FirebaseRepository()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Live Bus Ping",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = busId,
            onValueChange = { busId = it },
            label = { Text("Bus ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = driverName,
            onValueChange = { driverName = it },
            label = { Text("Driver Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = routeName,
            onValueChange = { routeName = it },
            label = { Text("Route Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = currentStop,
            onValueChange = { currentStop = it },
            label = { Text("Current Stop") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = etaMinutes,
            onValueChange = { etaMinutes = it },
            label = { Text("ETA Minutes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val busPing = BusPing(
                    busId = busId,
                    driverName = driverName,
                    routeName = routeName,
                    stopName = currentStop,
                    etaMinutes = etaMinutes.toIntOrNull() ?: 0,

                    latitude = 12.9716,
                    longitude = 77.5946
                )

                repository.sendBusPing(busPing)

                Toast.makeText(
                    context,
                    "Bus Ping Sent",
                    Toast.LENGTH_SHORT
                ).show()

                busId = ""
                driverName = ""
                routeName = ""
                currentStop = ""
                etaMinutes = ""
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Send Bus Ping")
        }
    }
}