package com.example.gramayatri.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Security
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

import androidx.compose.foundation.clickable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramayatri.viewmodel.AdminViewModel

@Composable
fun AdminDashboard(
    navController: NavController,
    viewModel: AdminViewModel = viewModel()
) {

    val analytics by viewModel.analytics.collectAsState()
    val emergencies by viewModel.emergencies.collectAsState()

    val activity = LocalContext.current as Activity

    BackHandler {
        activity.moveTaskToBack(true)
    }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF06111F),
            Color(0xFF0B1727),
            Color(0xFF111827)
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
                    text = "Admin Control Center",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Realtime Mobility Intelligence Platform",
                    color = Color(0xFFCBD5E1),
                    fontSize = 14.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF7C3AED))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {

                Text(
                    text = "SUPER ADMIN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        AdminMetricCard(
            title = "Active Live Buses",
            value = analytics.activeBuses.toString(),
            icon = Icons.Default.DirectionsBus,
            accent = Color(0xFF2563EB)
        )

        Spacer(modifier = Modifier.height(18.dp))

        AdminMetricCard(
            title = "Active Conductors",
            value = analytics.activeConductors.toString(),
            icon = Icons.Default.Groups,
            accent = Color(0xFF059669)
        )

        Spacer(modifier = Modifier.height(18.dp))

        AdminMetricCard(
            title = "Operational Routes",
            value = analytics.operationalRoutes.toString(),
            icon = Icons.Default.Route,
            accent = Color(0xFFF59E0B)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Operations Management",
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        AdminActionCard(
            title = "Fleet Monitoring",
            subtitle = "Track realtime live buses and route operations",
            icon = Icons.Default.DirectionsBus,
            accent = Color(0xFF2563EB),
            onClick = { navController.navigate("admin_fleet_map") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AdminActionCard(
            title = "Conductor Management",
            subtitle = "Manage transport operators and live assignments",
            icon = Icons.Default.Groups,
            accent = Color(0xFF059669),
            onClick = { navController.navigate("admin_conductors") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AdminActionCard(
            title = "Passenger Intelligence",
            subtitle = "Manage accounts, history and analytics",
            icon = Icons.Default.Groups,
            accent = Color(0xFF0EA5E9),
            onClick = { navController.navigate("admin_passengers") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AdminActionCard(
            title = "AI Transit Analytics",
            subtitle = "Realtime operational intelligence and ETA systems",
            icon = Icons.Default.Analytics,
            accent = Color(0xFF7C3AED),
            onClick = { navController.navigate("admin_analytics") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AdminActionCard(
            title = "QR Intelligence",
            subtitle = "Monitor ticket validation and fraud detection",
            icon = Icons.Default.AdminPanelSettings,
            accent = Color(0xFF10B981),
            onClick = { navController.navigate("admin_qr_validation") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AdminActionCard(
            title = "Emergency Control",
            subtitle = "${emergencies.filter { it.status == "ACTIVE" }.size} Active Alerts - Monitor SOS signals",
            icon = Icons.Default.NotificationsActive,
            accent = Color(0xFFDC2626),
            onClick = { navController.navigate("admin_emergencies") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AdminActionCard(
            title = "Fleet Broadcast",
            subtitle = "Send announcements and emergency alerts to all users",
            icon = Icons.Default.NotificationsActive,
            accent = Color(0xFFF59E0B),
            onClick = { navController.navigate("admin_notifications") }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "System Infrastructure",
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            StatusCard(
                modifier = Modifier.weight(1f),
                title = "Realtime Sync",
                value = "ONLINE",
                color = Color(0xFF059669)
            )

            Spacer(modifier = Modifier.width(14.dp))

            StatusCard(
                modifier = Modifier.weight(1f),
                title = "Cloud",
                value = "STABLE",
                color = Color(0xFF2563EB)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            StatusCard(
                modifier = Modifier.weight(1f),
                title = "AI Engine",
                value = "READY",
                color = Color(0xFF7C3AED)
            )

            Spacer(modifier = Modifier.width(14.dp))

            StatusCard(
                modifier = Modifier.weight(1f),
                title = "Security",
                value = "ACTIVE",
                color = Color(0xFFF59E0B)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun AdminMetricCard(
    title: String,
    value: String,
    icon: ImageVector,
    accent: Color
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(26.dp),

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
                    .size(58.dp)
                    .background(
                        accent,
                        shape = RoundedCornerShape(18.dp)
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

            Column {

                Text(
                    text = title,
                    color = Color(0xFFCBD5E1),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun AdminActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },

        shape = RoundedCornerShape(24.dp),

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
                    .size(56.dp)
                    .background(
                        accent,
                        shape = RoundedCornerShape(18.dp)
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

            Column {

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
        }
    }
}

@Composable
fun StatusCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    color: Color
) {

    Card(
        modifier = modifier,

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF172033)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                color = Color(0xFFCBD5E1),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = value,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}