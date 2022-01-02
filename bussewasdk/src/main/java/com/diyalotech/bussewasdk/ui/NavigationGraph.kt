package com.diyalotech.bussewasdk.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diyalotech.bussewasdk.LibraryComponent
import com.diyalotech.bussewasdk.ui.bookingcustomer.BookingConfirmView
import com.diyalotech.bussewasdk.ui.bookingcustomer.BookingConfirmViewModel
import com.diyalotech.bussewasdk.ui.di.daggerViewModel
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
    startDestination: String = NavDestinations.HOME_ROUTE,
    component: LibraryComponent
) {

    NavHost(navController, startDestination) {
        composable(NavDestinations.HOME_ROUTE) {

            val searchTripViewModel: SearchTripViewModel = daggerViewModel(NavDestinations.HOME_ROUTE) {
                component.getSearchTripVM()
            }

            HomeView(
                searchTripViewModel = searchTripViewModel,
                onSearchClicked = navActions.navigateToTripList,
                onLocationClicked = navActions.navigateToSearchLocation
            )
        }

        composable(NavDestinations.SEARCH_LOCATION_ROUTE) {

            val searchLocationViewModel: SearchLocationViewModel = daggerViewModel(NavDestinations.SEARCH_LOCATION_ROUTE) {
                component.getSearchLocationVM()
            }

            SearchLocationView(
                viewModel = searchLocationViewModel,
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(NavDestinations.TRIP_LIST_ROUTE) {
            val tripLisViewModel: TripListViewModel = daggerViewModel(NavDestinations.TRIP_LIST_ROUTE) {
                component.getTripListVM()
            }
            TripListView(
                tripListViewModel = tripLisViewModel,
                onBackPressed = { navController.popBackStack() },
                onTripClicked = { trip ->
                    tripLisViewModel.onTripClicked(trip)
                    navActions.navigateToSeatSelection()
                }
            )
        }

        composable(NavDestinations.SEAT_LAYOUT_ROUTE) {
            val selectSeatsViewModel: SelectSeatsViewModel = daggerViewModel(NavDestinations.TRIP_LIST_ROUTE) {
                component.getSelectSeatsVM()
            }
            SelectSeatsView(
                selectSeatsViewModel,
                onNavToConfirmBooking = navActions.navigateToBookConfirmation,
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(NavDestinations.BOOKING_CONFIRMATION_ROUTE) {
            val bookingConfirmViewModel: BookingConfirmViewModel = daggerViewModel(NavDestinations.TRIP_LIST_ROUTE) {
                component.getBookingCustomerVM()
            }
            BookingConfirmView(
                bookingConfirmViewModel,
                onBackPressed = { navController.popBackStack() },
            )
        }

    }
}