package com.example.gramayatri.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramayatri.data.models.BusModel
import com.example.gramayatri.ui.components.*
import com.example.gramayatri.viewmodel.BookingViewModel
import com.example.gramayatri.viewmodel.PassengerDashboardViewModel
import com.example.gramayatri.viewmodel.PassengerViewModel
import com.google.firebase.database.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerDashboardScreen(
    navController: NavController,
    viewModel: PassengerDashboardViewModel = viewModel(),
    passengerViewModel: PassengerViewModel = viewModel(),
    bookingViewModel: BookingViewModel = viewModel()
) {
    val liveBuses by viewModel.liveBuses.collectAsState()
    val recommendedRoutes by viewModel.recommendedRoutes.collectAsState()
    val isEmergencyActive by viewModel.isEmergencyActive.collectAsState()

    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedBus by remember { mutableStateOf<BusModel?>(null) }
    val sheetState = rememberModalBottomSheetState()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF020617),
            Color(0xFF071120),
            Color(0xFF0F172A)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        // Only show Map on Home Tab
        if (selectedTab == 0) {
            HomeMapScreen(viewModel)
        }

        when (selectedTab) {
            0 -> { // HOME
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 18.dp),
                    contentPadding = PaddingValues(top = 48.dp, bottom = 120.dp)
                ) {
                    item {
                        SearchBar(
                            query = passengerViewModel.searchQuery,
                            onQueryChange = { passengerViewModel.updateSearch(it) },
                            onSearchClick = {
                                Toast.makeText(context, "Searching for: ${passengerViewModel.searchQuery}", Toast.LENGTH_SHORT).show()
                            }
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                    }

                    item {
                        RealtimeIndicator()
                        Spacer(modifier = Modifier.height(18.dp))
                    }

                    if (liveBuses.isNotEmpty()) {
                        item {
                            PremiumETAWidget(
                                bus = liveBuses.first(),
                                onClick = { navController.navigate("live_tracking") }
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }

                    item {
                        Text(
                            text = "Nearby Live Buses",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(passengerViewModel.filterBuses(liveBuses)) { bus ->
                                BusCard(
                                    bus = bus,
                                    onBookSeat = {
                                        selectedBus = bus
                                        showBottomSheet = true
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    item {
                        Text(
                            text = "Recommended Routes",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    items(recommendedRoutes) { route ->
                        RouteCard(
                            route = route,
                            onRouteClick = {
                                Toast.makeText(context, "Route details: ${route.source} to ${route.destination}", Toast.LENGTH_SHORT).show()
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        EmergencyAssistanceCard(
                            onSOSClick = {
                                viewModel.triggerEmergency("p123", "User", null, 15.3, 76.4)
                                Toast.makeText(context, "SOS Alert Sent to Control Center", Toast.LENGTH_LONG).show()
                            }
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
            1 -> TripsScreen()
            2 -> RoutesScreen()
            3 -> AlertsScreen()
            4 -> ProfileScreen(navController)
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 18.dp)
        ) {
            MobilityBottomNav(
                selectedIndex = selectedTab,
                onItemSelected = { index -> selectedTab = index }
            )
        }

        if (showBottomSheet && selectedBus != null) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color(0xFF0F172A)
            ) {
                PaymentBottomSheet(
                    busNumber = selectedBus!!.busNumber,
                    route = selectedBus!!.route,
                    onPaymentSuccess = {
                        bookingViewModel.bookSeat(
                            busNumber = selectedBus!!.busNumber,
                            route = selectedBus!!.route,
                            passengerCount = 1
                        )
                        Toast.makeText(context, "Booking Confirmed!", Toast.LENGTH_SHORT).show()
                        showBottomSheet = false
                        navController.navigate("ticket_screen")
                    }
                )
            }
        }
    }
}