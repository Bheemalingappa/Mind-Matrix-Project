package com.example.gramayatri.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gramayatri.auth.CreateAccountScreen
import com.example.gramayatri.auth.LoginScreen
import com.example.gramayatri.ui.screens.AdminAnalyticsScreen
import com.example.gramayatri.ui.screens.AdminConductorScreen
import com.example.gramayatri.ui.screens.AdminDashboard
import com.example.gramayatri.ui.screens.AdminEmergencyScreen
import com.example.gramayatri.ui.screens.AdminFleetMapScreen
import com.example.gramayatri.ui.screens.AdminPassengerScreen
import com.example.gramayatri.ui.screens.AdminQRValidationScreen
import com.example.gramayatri.ui.screens.ConductorDashboard
import com.example.gramayatri.ui.screens.LandingScreen
import com.example.gramayatri.ui.screens.LiveTrackingScreen
import com.example.gramayatri.ui.screens.PassengerDashboardScreen
import com.example.gramayatri.ui.screens.PassengerProfileScreen
import com.example.gramayatri.ui.screens.PassengerTicketScreen
import com.example.gramayatri.ui.screens.QRScannerScreen
import com.example.gramayatri.ui.screens.SettingsScreen
import com.example.gramayatri.ui.screens.WalletScreen

import com.example.gramayatri.ui.screens.AdminNotificationScreen

@Composable
fun AppNavigation() {

    val navController: NavHostController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "landing"
    ) {

        /**
         * LANDING SCREEN
         */
        composable("landing") {

            LandingScreen(

                onPassengerClick = {

                    navController.navigate(
                        "login/passenger"
                    ) {

                        launchSingleTop = true
                    }
                },

                onConductorClick = {

                    navController.navigate(
                        "login/conductor"
                    ) {

                        launchSingleTop = true
                    }
                },

                onAdminClick = {

                    navController.navigate(
                        "login/admin"
                    ) {

                        launchSingleTop = true
                    }
                }
            )
        }

        /**
         * LOGIN SCREEN
         */
        composable(

            route = "login/{role}",

            arguments = listOf(

                navArgument("role") {

                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->

            val role =
                backStackEntry.arguments
                    ?.getString("role")
                    ?: "passenger"

            LoginScreen(

                selectedRole = role,

                onLoginSuccess = {

                    when (role) {

                        "passenger" -> {

                            navController.navigate(
                                "passenger_dashboard"
                            ) {

                                launchSingleTop = true
                            }
                        }

                        "conductor" -> {

                            navController.navigate(
                                "conductor_dashboard"
                            ) {

                                launchSingleTop = true
                            }
                        }

                        "admin" -> {

                            navController.navigate(
                                "admin_dashboard"
                            ) {

                                launchSingleTop = true
                            }
                        }
                    }
                },

                onCreateAccountClick = {

                    if (role == "passenger") {

                        navController.navigate(
                            "create_account"
                        ) {

                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        /**
         * CREATE ACCOUNT
         */
        composable("create_account") {

            CreateAccountScreen(

                onLoginClick = {

                    navController.popBackStack()
                }
            )
        }

        /**
         * PASSENGER DASHBOARD
         */
        composable("passenger_dashboard") {

            PassengerDashboardScreen(navController)
        }

        /**
         * LIVE TRACKING SCREEN
         */
        composable("live_tracking") {

            LiveTrackingScreen()
        }

        /**
         * CONDUCTOR DASHBOARD
         */
        composable("conductor_dashboard") {

            ConductorDashboard()
        }

        /**
         * ADMIN DASHBOARD
         */
        composable("admin_dashboard") {

            AdminDashboard(navController)
        }

        /**
         * ADMIN FLEET MAP
         */
        composable("admin_fleet_map") {

            AdminFleetMapScreen(navController)
        }

        /**
         * ADMIN CONDUCTOR MANAGEMENT
         */
        composable("admin_conductors") {

            AdminConductorScreen(navController)
        }

        /**
         * ADMIN PASSENGER INTELLIGENCE
         */
        composable("admin_passengers") {

            AdminPassengerScreen(navController)
        }

        /**
         * ADMIN EMERGENCY CONTROL
         */
        composable("admin_emergencies") {

            AdminEmergencyScreen(navController)
        }

        /**
         * ADMIN ANALYTICS
         */
        composable("admin_analytics") {

            AdminAnalyticsScreen(navController)
        }

        /**
         * ADMIN NOTIFICATIONS
         */
        composable("admin_notifications") {

            AdminNotificationScreen(navController)
        }

        /**
         * ADMIN QR VALIDATION
         */
        composable("admin_qr_validation") {

            AdminQRValidationScreen(navController)
        }

        /**
         * PASSENGER TICKET SCREEN
         */
        composable("ticket_screen") {

            PassengerTicketScreen()
        }

        /**
         * WALLET
         */
        composable("wallet") {
            WalletScreen(navController)
        }

        /**
         * SETTINGS
         */
        composable("settings_screen") {
            SettingsScreen()
        }

        /**
         * QR SCANNER
         */
        composable("qr_scanner") {

            QRScannerScreen()
        }
    }
}