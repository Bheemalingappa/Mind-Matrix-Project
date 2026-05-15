package com.example.gramayatri.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ReportBusScreen() {

    val context = LocalContext.current

    var busNumber by remember {
        mutableStateOf("")
    }

    var routeName by remember {
        mutableStateOf("")
    }

    var stopName by remember {
        mutableStateOf("")
    }

    var eta by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Report Bus",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = busNumber,
            onValueChange = {
                busNumber = it
            },
            label = {
                Text("Bus Number")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = routeName,
            onValueChange = {
                routeName = it
            },
            label = {
                Text("Route Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = stopName,
            onValueChange = {
                stopName = it
            },
            label = {
                Text("Current Stop")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = eta,
            onValueChange = {
                eta = it
            },
            label = {
                Text("ETA Minutes")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {


                val database =
                    FirebaseDatabase.getInstance()

                val ref =
                    database.getReference("bus_reports")

                val reportId = ref.push().key

                val reportData = mapOf(
                    "reporter" to FirebaseAuth.getInstance().currentUser?.email,
                    "timestamp" to System.currentTimeMillis(),
                    "busNumber" to busNumber,
                    "routeName" to routeName,
                    "stopName" to stopName,
                    "eta" to eta
                )

                if (reportId != null) {

                    ref.child(reportId)
                        .setValue(reportData)
                        .addOnSuccessListener {

                            Toast.makeText(
                                context,
                                "Bus Report Submitted",
                                Toast.LENGTH_SHORT
                            ).show()

                            busNumber = ""
                            routeName = ""
                            stopName = ""
                            eta = ""
                        }
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Submit Report")
        }
    }
}