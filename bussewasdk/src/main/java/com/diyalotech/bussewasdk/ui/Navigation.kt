package com.diyalotech.bussewasdk.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

object NavDestinations {
    const val HOME_ROUTE = "home"
    const val TRIP_LIST_ROUTE = "trip_list"
    const val SEAT_LAYOUT = "seat_layout"
}

class Navigation(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(NavDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToTripList: () -> Unit = {
        navController.navigate(NavDestinations.TRIP_LIST_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}