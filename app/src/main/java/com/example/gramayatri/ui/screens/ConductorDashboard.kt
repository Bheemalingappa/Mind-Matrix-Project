package com.example.gramayatri.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.compose.rememberNavController
import com.example.gramayatri.data.repository.JourneyRepository
import com.example.gramayatri.services.LocationService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ConductorDashboard() {

    /**
     * CONTEXT
     */
    val context = LocalContext.current

    /**
     * NAVIGATION
     */
    val navController =
        rememberNavController()

    /**
     * ACTIVITY
     */
    val activity =
        context as Activity

    /**
     * BACK HANDLER
     */
    BackHandler {

        activity.moveTaskToBack(true)
    }

    /**
     * JOURNEY STATE
     */
    var isJourneyActive by remember {

        mutableStateOf(false)
    }

    /**
     * FIREBASE
     */
    val auth =
        FirebaseAuth.getInstance()

    val database =
        FirebaseDatabase.getInstance()

    val currentUser =
        auth.currentUser

    val conductorId =
        currentUser?.uid ?: "unknown"

    /**
     * ASSIGNED BUS
     */
    val assignedBus =
        "KA-34-B-1021"

    val assignedRoute =
        "Hospete → Ballari"

    /**
     * LOCATION SERVICE
     */
    val locationService = remember {

        LocationService(context)
    }

    /**
     * JOURNEY REPOSITORY
     */
    val journeyRepository = remember {

        JourneyRepository()
    }

    /**
     * START LIVE GPS
     */
    LaunchedEffect(Unit) {

        locationService.startLocationUpdates(

            conductorId = conductorId,

            busNumber = assignedBus,

            route = assignedRoute
        )
    }

    /**
     * BACKGROUND
     */
    val backgroundBrush = Brush.verticalGradient(

        colors = listOf(

            Color(0xFF06111F),

            Color(0xFF0B1727),

            Color(0xFF111827)
        )
    )

    /**
     * UPDATE LIVE BUS
     */
    fun updateLiveBus(
        active: Boolean
    ) {

        val liveBusData = hashMapOf(

            "busNumber" to assignedBus,

            "route" to assignedRoute,

            "conductorId" to conductorId,

            "journeyActive" to active,

            "status" to
                if (active)
                    "LIVE"
                else
                    "OFFLINE",

            "latitude" to 15.1394,

            "longitude" to 76.9214
        )

        database.reference

            .child("live_buses")

            .child(
                assignedBus.replace("-", "_")
            )

            .setValue(liveBusData)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        /**
         * HEADER
         */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Conductor Console",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Realtime Fleet Operations Platform",
                    color = Color(0xFFCBD5E1),
                    fontSize = 14.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (isJourneyActive)
                            Color(0xFF065F46)
                        else
                            Color(0xFF7F1D1D)
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    )
            ) {

                Text(
                    text =
                        if (isJourneyActive)
                            "LIVE"
                        else
                            "OFFLINE",

                    color = Color.White,

                    fontWeight = FontWeight.Bold,

                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        /**
         * BUS INFO
         */
        FleetInfoCard(
            title = "Assigned Bus",
            value = assignedBus,
            icon = Icons.Default.DirectionsBus,
            accent = Color(0xFF4F46E5)
        )

        Spacer(modifier = Modifier.height(18.dp))

        FleetInfoCard(
            title = "Assigned Route",
            value = assignedRoute,
            icon = Icons.Default.BusAlert,
            accent = Color(0xFF0F766E)
        )

        Spacer(modifier = Modifier.height(18.dp))

        FleetInfoCard(
            title = "Journey Status",

            value =
                if (isJourneyActive)
                    "Journey Active"
                else
                    "Awaiting Dispatch",

            icon = Icons.Default.Timeline,

            accent =
                if (isJourneyActive)
                    Color(0xFF059669)
                else
                    Color(0xFFB91C1C)
        )

        Spacer(modifier = Modifier.height(32.dp))

        /**
         * OPERATIONS TITLE
         */
        Text(
            text = "Operations Control",
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        /**
         * START JOURNEY
         */
        FleetActionButton(
            text = "Start Journey",
            icon = Icons.Default.PlayArrow,
            color = Color(0xFF059669)
        ) {

            isJourneyActive = true

            journeyRepository.startJourney(
                assignedBus
            )

            updateLiveBus(true)

            Toast.makeText(
                context,
                "Journey Started",
                Toast.LENGTH_SHORT
            ).show()
        }

        Spacer(modifier = Modifier.height(14.dp))

        /**
         * END JOURNEY
         */
        FleetActionButton(
            text = "End Journey",
            icon = Icons.Default.Stop,
            color = Color(0xFFDC2626)
        ) {

            isJourneyActive = false

            journeyRepository.endJourney(
                assignedBus
            )

            updateLiveBus(false)

            Toast.makeText(
                context,
                "Journey Ended",
                Toast.LENGTH_SHORT
            ).show()
        }

        Spacer(modifier = Modifier.height(14.dp))

        /**
         * REALTIME SYNC
         */
        FleetActionButton(
            text = "Realtime Sync Update",
            icon = Icons.Default.Sync,
            color = Color(0xFF2563EB)
        ) {

            updateLiveBus(isJourneyActive)

            Toast.makeText(
                context,
                "Realtime Fleet Sync Completed",
                Toast.LENGTH_SHORT
            ).show()
        }

        Spacer(modifier = Modifier.height(14.dp))

        /**
         * EMERGENCY ALERT
         */
        FleetActionButton(
            text = "Send Emergency Alert",
            icon = Icons.Default.Emergency,
            color = Color(0xFFF59E0B)
        ) {

            Toast.makeText(
                context,
                "Emergency Operations Alert Triggered",
                Toast.LENGTH_SHORT
            ).show()
        }

        Spacer(modifier = Modifier.height(14.dp))

        /**
         * QR SCANNER
         */
        FleetActionButton(
            text = "Open QR Scanner",
            icon = Icons.Default.Sync,
            color = Color(0xFF6366F1)
        ) {

            navController.navigate(
                "qr_scanner"
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        /**
         * STATUS TITLE
         */
        Text(
            text = "Realtime Operational Status",
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        /**
         * STATUS ROW 1
         */
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            FleetStatusCard(
                modifier = Modifier.weight(1f),
                title = "GPS",
                value = "ACTIVE",
                color = Color(0xFF059669)
            )

            Spacer(modifier = Modifier.width(14.dp))

            FleetStatusCard(
                modifier = Modifier.weight(1f),
                title = "Network",
                value = "STABLE",
                color = Color(0xFF2563EB)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        /**
         * STATUS ROW 2
         */
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            FleetStatusCard(
                modifier = Modifier.weight(1f),
                title = "Fleet Sync",
                value = "LIVE",
                color = Color(0xFF7C3AED)
            )

            Spacer(modifier = Modifier.width(14.dp))

            FleetStatusCard(
                modifier = Modifier.weight(1f),
                title = "ETA AI",
                value = "READY",
                color = Color(0xFFF59E0B)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun FleetInfoCard(
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
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun FleetActionButton(
    text: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),

        shape = RoundedCornerShape(20.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun FleetStatusCard(
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