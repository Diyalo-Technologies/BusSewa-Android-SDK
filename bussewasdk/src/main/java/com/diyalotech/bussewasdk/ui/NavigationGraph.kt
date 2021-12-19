package com.diyalotech.bussewasdk.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diyalotech.bussewasdk.ui.locationlist.SearchLocationView
import com.diyalotech.bussewasdk.ui.locationlist.SearchLocationViewModel
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatsView
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatsViewModel
import com.diyalotech.bussewasdk.ui.triplist.TripListView
import com.diyalotech.bussewasdk.ui.triplist.TripListViewModel

@Composable
fun NavigationGraph(
    navActions: BusNavigationActions,
    navController: NavHostController,
    startDestination: String = NavDestinations.HOME_ROUTE
) {
    NavHost(navController, startDestination) {
        composable(NavDestinations.HOME_ROUTE) {

            val searchTripViewModel: SearchTripViewModel = hiltViewModel()

            HomeView(
                searchTripViewModel = searchTripViewModel,
                onSearchClicked = navActions.navigateToTripList,
                onLocationClicked = navActions.navigateToSearchLocation
            )
        }

        composable(NavDestinations.SEARCH_LOCATION_ROUTE) {

            val searchLocationViewModel: SearchLocationViewModel = hiltViewModel()

            SearchLocationView(
                viewModel = searchLocationViewModel,
                onBackPressed = { navController.popBackStack() },
                onSelected = { searchLocationViewModel.onSearchChanged(it) }
            )
        }

        composable(NavDestinations.TRIP_LIST_ROUTE) {
            val tripLisViewModel: TripListViewModel = hiltViewModel()
            TripListView(
                tripListViewModel = tripLisViewModel,
                onBackPressed = { navController.popBackStack() },
                onTripClicked = {
                    tripLisViewModel.onTripClicked(it)
                    navActions.navigateToSeatSelection()
                }
            )
        }

        composable(NavDestinations.SEAT_LAYOUT_ROUTE) {
            val selectSeatsViewModel: SelectSeatsViewModel = hiltViewModel()
            SelectSeatsView(
                selectSeatsViewModel,
                onBackPressed = { navController.popBackStack() }
            )
        }

    }
}