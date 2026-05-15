package com.example.gramayatri.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramayatri.R

@Composable
fun HomeScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF3E5F5),
                        Color.White
                    )
                )
            )
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "Welcome to Grama-Yatri",

                style = MaterialTheme.typography.headlineMedium,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF6A1B9A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text =
                    "Community Powered Rural Bus Tracking",

                color = Color.DarkGray,

                fontSize = 16.sp
            )
        }

        item {

            Image(
                painter = painterResource(
                    id = R.drawable.banner
                ),

                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(24.dp)),

                contentScale = ContentScale.Crop
            )
        }

        item {

            ModernBusCard(
                busNumber = "BUS101",
                route = "Hosapete → Ballari",
                eta = "10 mins",
                status = "LIVE"
            )
        }

        item {

            ModernBusCard(
                busNumber = "BUS202",
                route = "Kudligi → Vijayanagara",
                eta = "18 mins",
                status = "ON TIME"
            )
        }

        item {

            Card(
                shape = RoundedCornerShape(24.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Route,

                            contentDescription = null,

                            tint = Color(0xFF6A1B9A)
                        )

                        Spacer(
                            modifier = Modifier.padding(6.dp)
                        )

                        Text(
                            text = "Active Routes",

                            style =
                                MaterialTheme.typography
                                    .titleMedium,

                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text("• Hosapete → Ballari")

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text("• Kudligi → Vijayanagara")

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text("• Rural Express Route")
                }
            }
        }

        item {

            Card(
                shape = RoundedCornerShape(24.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Notifications,

                            contentDescription = null,

                            tint = Color(0xFFD32F2F)
                        )

                        Spacer(
                            modifier = Modifier.padding(6.dp)
                        )

                        Text(
                            text = "Latest Alerts",

                            style =
                                MaterialTheme.typography
                                    .titleMedium,

                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text =
                            "⚠ BUS101 delayed by 10 mins"
                    )

                    Divider(
                        modifier = Modifier.padding(
                            vertical = 10.dp
                        )
                    )

                    Text(
                        text =
                            "⚠ Route to Ballari crowded today"
                    )

                    Divider(
                        modifier = Modifier.padding(
                            vertical = 10.dp
                        )
                    )

                    Text(
                        text =
                            "✅ Rural Express running on time"
                    )
                }
            }
        }
    }
}

@Composable
fun ModernBusCard(
    busNumber: String,
    route: String,
    eta: String,
    status: String
) {

    Card(
        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Icon(
                imageVector =
                    Icons.Default.DirectionsBus,

                contentDescription = null,

                modifier = Modifier.size(50.dp),

                tint = Color(0xFF6A1B9A)
            )

            Spacer(
                modifier = Modifier.padding(8.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = busNumber,

                    style =
                        MaterialTheme.typography
                            .titleLarge,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = route,

                    color = Color.DarkGray
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "ETA: $eta",

                    color = Color(0xFF2E7D32),

                    fontWeight = FontWeight.Bold
                )
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                ),

                shape = RoundedCornerShape(12.dp)
            ) {

                Text(
                    text = status,

                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    ),

                    color = Color(0xFF2E7D32),

                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}