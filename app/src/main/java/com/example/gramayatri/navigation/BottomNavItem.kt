package com.example.gramayatri.navigation

sealed class BottomNavItem(
    val route: String
) {

    object Home : BottomNavItem("home")

    object Routes : BottomNavItem("routes")

    object Trips : BottomNavItem("trips")

    object Alerts : BottomNavItem("alerts")

    object Profile : BottomNavItem("profile")
}