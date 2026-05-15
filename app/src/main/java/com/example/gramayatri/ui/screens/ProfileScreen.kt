package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramayatri.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val profile by profileViewModel.profile.collectAsState()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF020617),
            Color(0xFF0F172A)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFF2563EB)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(70.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(profile?.name ?: "GramaYatri Passenger", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("${profile?.membership ?: "REGULAR"} Member", color = Color(0xFF10B981), fontSize = 14.sp)

        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatItem("Loyalty", "${profile?.loyaltyPoints ?: 0}")
            StatItem("Distance", "${profile?.totalDistanceTraveled ?: 0.0} km")
            StatItem("Tickets", "${profile?.totalTickets ?: 0}")
        }

        Spacer(modifier = Modifier.height(30.dp))

        ProfileOptionItem(Icons.Default.History, "Trip History", "View your past journeys") {
            // Usually handled by tab switching in Dashboard
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOptionItem(Icons.Default.AccountBalanceWallet, "Wallet: ₹${profile?.walletBalance ?: 0.0}", "Manage credits and payments") {
            navController.navigate("wallet")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOptionItem(Icons.Default.Favorite, "Saved Routes", "Quick access to your favorites") {
            // Usually handled by tab switching in Dashboard
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOptionItem(Icons.Default.Settings, "Settings", "Notifications and preferences") {
            navController.navigate("settings_screen")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { profileViewModel.signOut() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Logout", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(label, color = Color.Gray, fontSize = 12.sp)
    }
}

@Composable
fun ProfileOptionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
                    .size(50.dp)
                    .background(
                        Color(0xFF2563EB).copy(alpha = 0.1f),
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF3B82F6)
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    color = Color(0xFFCBD5E1),
                    fontSize = 12.sp
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}
