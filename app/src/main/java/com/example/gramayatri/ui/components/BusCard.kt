package com.example.gramayatri.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramayatri.data.models.BusModel

@Composable
fun BusCard(

    bus: BusModel,

    onBookSeat: () -> Unit
) {

    val seatsLeft =
        bus.totalSeats - bus.bookedSeats

    Card(

        modifier = Modifier
            .width(260.dp)
            .padding(end = 16.dp),

        colors = CardDefaults.cardColors(

            containerColor = Color(0xFF14213D)
        ),

        shape = RoundedCornerShape(22.dp)
    ) {

        Column(

            modifier = Modifier.padding(18.dp)
        ) {

            /**
             * BUS NUMBER
             */
            Text(

                text = bus.busNumber,

                color = Color.White,

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            /**
             * ROUTE
             */
            Text(

                text = bus.route,

                color = Color.LightGray
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            /**
             * ETA
             */
            Text(

                text = "Arriving in ${bus.eta}",

                color = Color(0xFF00E5A8),

                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            /**
             * LIVE SEAT STATUS
             */
            Text(

                text = "$seatsLeft Seats Left",

                color =

                if (seatsLeft <= 5)

                    Color.Red

                else

                    Color(0xFFFFC107),

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            /**
             * BOOK BUTTON
             */
            Button(

                onClick = {

                    onBookSeat()
                },

                colors = ButtonDefaults.buttonColors(

                    containerColor = Color(0xFF2962FF)
                ),

                shape = RoundedCornerShape(16.dp),

                modifier = Modifier.fillMaxWidth()
            ) {

                Text(

                    text =

                    if (seatsLeft <= 5)

                        "Few Seats Left"

                    else

                        "Book Seat",

                    color = Color.White
                )
            }
        }
    }
}