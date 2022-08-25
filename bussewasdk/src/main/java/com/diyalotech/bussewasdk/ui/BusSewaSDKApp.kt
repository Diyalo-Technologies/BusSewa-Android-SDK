package com.diyalotech.bussewasdk.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.diyalotech.bussewasdk.LibraryComponent
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
internal fun BusSewaSDKApp(component: LibraryComponent) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        BusNavigationActions(navController)
    }


    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colors.background
    ) {
        NavigationGraph(navigationActions, navController, component = component)
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