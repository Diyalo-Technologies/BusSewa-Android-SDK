package com.diyalotech.bussewasdk.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun BusSewaSDKApp() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        BusNavigationActions(navController)
    }

    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: NavDestinations.HOME_ROUTE


    ProvideWindowInsets {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background
        ) {
            NavigationGraph(navigationActions, navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BusAppPreview() {
    BusSewaSDKTheme {
        /*val searchTripViewModel = SearchTripViewModel()
        Greeting(searchTripViewModel, {}) {}*/
    }
}