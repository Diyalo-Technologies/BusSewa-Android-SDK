package com.diyalotech.bussewasdk.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diyalotech.bussewasdk.LibraryComponent
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_MESSAGE
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_RESPONSE
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
internal fun NavigationGraph(
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

            val activity = (LocalContext.current as Activity?)

            HomeView(
                searchTripViewModel = searchTripViewModel,
                onNavigateToTrip = navActions.navigateToTripList,
                onLocationClicked = navActions.navigateToSearchLocation
            ) {
                val data = Intent()
                data.putExtra(BUS_SDK_MESSAGE, "User cancelled.")
                activity?.setResult(AppCompatActivity.RESULT_CANCELED, data)
                activity?.finish()
            }
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