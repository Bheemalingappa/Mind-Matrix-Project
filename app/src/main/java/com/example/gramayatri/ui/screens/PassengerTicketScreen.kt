package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import com.example.gramayatri.utils.QRCodeGenerator

import androidx.compose.runtime.remember

@Composable
fun PassengerTicketScreen() {
    val qrBitmap = remember { QRCodeGenerator.generateQRCode("GY-TKT-KA34B1021-X9283") }
    val backgroundBrush = Brush.verticalGradient(
        listOf(
            Color(0xFF081120),
            Color(0xFF0F172A)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF10B981),
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Booking Confirmed", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // QR Code (Industrial Style)
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(Color.White, RoundedCornerShape(24.dp))
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        qrBitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "QR Code",
                                modifier = Modifier.fillMaxSize()
                            )
                        } ?: Icon(
                            imageVector = Icons.Default.QrCode,
                            contentDescription = "QR Code Error",
                            tint = Color.Black,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    TicketDetailRow("Bus Number", "KA-34-B-1021")
                    TicketDetailRow("Route", "Hospete → Ballari")
                    TicketDetailRow("Status", "VALIDATED")
                    TicketDetailRow("Valid Until", "Today, 11:59 PM")

                    Spacer(modifier = Modifier.height(20.dp))

                    HorizontalDivider(color = Color(0xFF334155), thickness = 1.dp)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("TICKET ID: GY-9283-X10", color = Color(0xFF94A3B8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { /* Save Offline */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Save to Offline Wallet", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun TicketDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color(0xFF94A3B8), fontSize = 14.sp)
        Text(value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}