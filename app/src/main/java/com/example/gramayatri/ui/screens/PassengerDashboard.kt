package com.example.gramayatri.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PassengerDashboard(
    onLiveTrackingClick: () -> Unit = {}
) {

    val activity = LocalContext.current as Activity

    BackHandler {

        activity.moveTaskToBack(true)
    }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF020617),
            Color(0xFF071120),
            Color(0xFF0F172A)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Grama-Yatri",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Smart Rural Mobility Intelligence",
                    color = Color(0xFF94A3B8),
                    fontSize = 14.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF065F46))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {

                Text(
                    text = "LIVE",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(34.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF172033)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF2563EB),
                                        Color(0xFF1D4ED8)
                                    )
                                ),
                                shape = RoundedCornerShape(24.dp)
                            ),

                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.DirectionsBus,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(34.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(18.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "KA-34-B-1021",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "Hospete → Ballari",
                            color = Color(0xFFCBD5E1),
                            fontSize = 14.sp
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "8 min",
                            color = Color(0xFF10B981),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "ETA",
                            color = Color(0xFF94A3B8),
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    LiveChip(
                        icon = Icons.Default.LocationOn,
                        text = "GPS"
                    )

                    LiveChip(
                        icon = Icons.Default.Speed,
                        text = "AI ETA"
                    )

                    LiveChip(
                        icon = Icons.Default.Navigation,
                        text = "Realtime"
                    )

                    LiveChip(
                        icon = Icons.Default.Wifi,
                        text = "Network"
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {

                        onLiveTrackingClick()
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),

                    shape = RoundedCornerShape(20.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB)
                    )
                ) {

                    Text(
                        text = "Open Live Tracking",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Passenger Services",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        PremiumServiceCard(
            title = "Smart ETA Intelligence",
            subtitle = "AI-powered predictive arrival system",
            icon = Icons.Default.AccessTime,
            accent = Color(0xFF059669)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PremiumServiceCard(
            title = "Realtime Route Timeline",
            subtitle = "Advanced stop progression intelligence",
            icon = Icons.Default.Timeline,
            accent = Color(0xFF7C3AED)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PremiumServiceCard(
            title = "Transit Alerts",
            subtitle = "Operational disruptions and live notifications",
            icon = Icons.Default.NotificationsActive,
            accent = Color(0xFFF59E0B)
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun PremiumServiceCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF172033)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        accent,
                        shape = RoundedCornerShape(20.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = subtitle,
                    color = Color(0xFFCBD5E1),
                    fontSize = 13.sp
                )
            }

            Text(
                text = "OPEN",
                color = accent,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LiveChip(
    icon: ImageVector,
    text: String
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF0F172A))
            .padding(horizontal = 12.dp, vertical = 8.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF60A5FA),
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}