package com.example.gramayatri.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gramayatri.navigation.BottomNavBar
import com.example.gramayatri.ui.screens.AlertScreen
import com.example.gramayatri.ui.screens.RouteTimelineScreen
import com.example.gramayatri.ui.screens.SettingsScreen
import com.example.gramayatri.ui.screens.TodayPingHistoryScreen

@Composable
fun DashboardScreen() {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(

        bottomBar = {

            BottomNavBar(
                selectedIndex = selectedIndex,

                onItemSelected = { index ->
                    selectedIndex = index
                }
            )
        }

    ) { paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            when (selectedIndex) {

                0 -> {
                    HomeScreen()
                }

                1 -> {
                    ReportBusScreen()
                }

                2 -> {
                    MapScreen()
                }

                3 -> {
                    RouteTimelineScreen()
                }

                4 -> {
                    AlertScreen()
                }

                5 -> {
                    TodayPingHistoryScreen()
                }

                6 -> {
                    SettingsScreen()
                }
            }
        }
    }
}