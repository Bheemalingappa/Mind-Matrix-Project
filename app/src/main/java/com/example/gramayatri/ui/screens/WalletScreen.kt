package com.example.gramayatri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramayatri.data.models.WalletTransactionModel
import com.example.gramayatri.viewmodel.ProfileViewModel
import com.example.gramayatri.viewmodel.WalletViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    navController: NavController,
    walletViewModel: WalletViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel()
) {
    val transactions by walletViewModel.transactions.collectAsState()
    val profile by profileViewModel.profile.collectAsState()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF020617), Color(0xFF0F172A))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Wallet", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF020617))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { walletViewModel.addTransaction(500.0, "CREDIT", "Wallet Recharge") },
                containerColor = Color(0xFF2563EB)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Funds", tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(padding)
        ) {
            // Balance Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total Balance", color = Color(0xFF94A3B8), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "₹${profile?.walletBalance ?: 0.0}",
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                "Recent Transactions",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(transactions) { tx ->
                    TransactionItem(tx)
                }
            }
        }
    }
}

@Composable
fun TransactionItem(tx: WalletTransactionModel) {
    val dateFormat = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    val dateString = dateFormat.format(Date(tx.timestamp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF14213D))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(tx.description, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(dateString, color = Color.Gray, fontSize = 12.sp)
            }
            Text(
                "${if (tx.type == "CREDIT") "+" else "-"} ₹${tx.amount}",
                color = if (tx.type == "CREDIT") Color(0xFF10B981) else Color(0xFFEF4444),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}