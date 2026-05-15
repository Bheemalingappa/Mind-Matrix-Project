package com.example.gramayatri.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TripBottomSheet() {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)
    ) {

        /**
         * TITLE
         */
        Text(

            text = "Confirm Booking",

            color = Color.White,

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        /**
         * BUS CARD
         */
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(

                containerColor = Color(0xFF14213D)
            )
        ) {

            Column(

                modifier = Modifier.padding(20.dp)
            ) {

                Row(

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(

                        imageVector = Icons.Default.DirectionsBus,

                        contentDescription = null,

                        tint = Color(0xFF3B82F6),

                        modifier = Modifier.size(36.dp)
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {

                        Text(

                            text = "KA-34-B-1021",

                            color = Color.White,

                            fontWeight = FontWeight.Bold,

                            fontSize = 22.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(

                            text = "Hospete → Ballari",

                            color = Color.LightGray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    BookingInfo(
                        title = "ETA",
                        value = "8 min"
                    )

                    BookingInfo(
                        title = "Fare",
                        value = "₹45"
                    )

                    BookingInfo(
                        title = "Seats",
                        value = "18 Left"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        /**
         * BOARDING
         */
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(22.dp),

            colors = CardDefaults.cardColors(

                containerColor = Color(0xFF14213D)
            )
        ) {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(

                    imageVector = Icons.Default.LocationOn,

                    contentDescription = null,

                    tint = Color(0xFF10B981)
                )

                Spacer(modifier = Modifier.width(14.dp))

                Column {

                    Text(

                        text = "Boarding Point",

                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(

                        text = "Hospete Central Bus Stand",

                        color = Color.White,

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        /**
         * PAYMENT
         */
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(22.dp),

            colors = CardDefaults.cardColors(

                containerColor = Color(0xFF14213D)
            )
        ) {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(

                    imageVector = Icons.Default.Payments,

                    contentDescription = null,

                    tint = Color(0xFFF59E0B)
                )

                Spacer(modifier = Modifier.width(14.dp))

                Column {

                    Text(

                        text = "Payment Method",

                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(

                        text = "UPI / Wallet / Card",

                        color = Color.White,

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        /**
         * CONFIRM BUTTON
         */
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFF2563EB),
                    RoundedCornerShape(20.dp)
                )
                .padding(vertical = 18.dp),

            contentAlignment = Alignment.Center
        ) {

            Text(

                text = "Confirm Booking",

                color = Color.White,

                fontWeight = FontWeight.Bold,

                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun BookingInfo(

    title: String,

    value: String
) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(

            text = title,

            color = Color.Gray,

            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(

            text = value,

            color = Color.White,

            fontWeight = FontWeight.Bold
        )
    }
}