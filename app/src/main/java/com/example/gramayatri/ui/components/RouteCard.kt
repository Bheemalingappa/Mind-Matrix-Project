package com.example.gramayatri.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramayatri.data.models.RouteModel

@Composable
fun RouteCard(
    route: RouteModel,
    onRouteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onRouteClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF14213D)
        ),
        shape = RoundedCornerShape(22.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "${route.source} → ${route.destination}",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${route.duration} • ${route.distance}",
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Fare : ${route.fare}",
                color = Color(0xFF00E5A8)
            )

            Text(
                text = "Traffic : ${route.trafficLevel}",
                color = Color.Yellow
            )
        }
    }
}