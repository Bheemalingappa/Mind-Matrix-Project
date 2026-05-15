package com.example.gramayatri.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll



@Composable
fun PaymentBottomSheet(

    busNumber: String,

    route: String,

    onPaymentSuccess: () -> Unit
) {

    var selectedPayment by remember {

        mutableStateOf("UPI")
    }

    Column(

    modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 650.dp)
        .verticalScroll(rememberScrollState())
        .background(Color(0xFF0F172A))
        .padding(20.dp)
) {

        Text(

            text = "Confirm Booking",

            color = Color.White,

            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(

            text = busNumber,

            color = Color.White,

            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(

            text = route,

            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(

            text = "Payment Method",

            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        val methods = listOf(

            "UPI",
            "Card",
            "Wallet",
            "Cash"
        )

        methods.forEach { method ->

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {

                        selectedPayment = method
                    },

                shape = RoundedCornerShape(16.dp)
            ) {

                Box(

                    modifier = Modifier
                        .background(

                            if (selectedPayment == method)
                                Color(0xFF2563EB)

                            else
                                Color(0xFF1E293B)
                        )
                        .padding(18.dp)
                ) {

                    Text(

                        text = method,

                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(

            onClick = {

                onPaymentSuccess()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = RoundedCornerShape(18.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2563EB)
            )
        ) {

            Text(
                text = "Pay & Confirm",
                color = Color.White
            )
        }
    }
}