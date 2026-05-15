package com.example.gramayatri.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRScannerScreen() {

    var scanResult by remember {

        mutableStateOf("No Ticket Scanned")
    }

    val scannerLauncher =

        rememberLauncherForActivityResult(

            contract = ScanContract()

        ) { result ->

            if (result.contents != null) {

                scanResult = result.contents
            }
        }

    Box(

        modifier = Modifier.fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {

        Column(

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(

                text = scanResult
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(

                onClick = {

                    val options =
                        ScanOptions()

                    options.setPrompt(
                        "Scan Passenger Ticket"
                    )

                    options.setBeepEnabled(true)

                    options.setOrientationLocked(true)

                    scannerLauncher.launch(
                        options
                    )
                }
            ) {

                Text("Start Scanner")
            }
        }
    }
}