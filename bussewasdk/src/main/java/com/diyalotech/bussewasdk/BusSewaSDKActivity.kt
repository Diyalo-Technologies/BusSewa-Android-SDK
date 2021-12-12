package com.diyalotech.bussewasdk

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.network.SeatLayoutDTO
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripView
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.seatlayout.SeatLayoutView
import com.diyalotech.bussewasdk.ui.seatlayout.testString
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate

class BusSewaSDKActivity : AppCompatActivity() {

    val searchTripViewModel: SearchTripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            BusSewaSDKTheme {
                ProvideWindowInsets {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        val searchTripState = searchTripViewModel.searchTripState.collectAsState()
                        Greeting(searchTripState.value) {
                            if (it == null) {
                                showDatePicker()
                            } else {
                                searchTripViewModel.setSearchDate(it)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .build()
            )
            .build()
        picker.show(supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener {

        }
    }
}

@Composable
fun Greeting(searchTripViewModel: SearchTripModel, onDateSelected: (LocalDate?) -> Unit) {
    val parsedSeatLayout = Gson().fromJson(testString, SeatLayoutDTO::class.java)
    println(parsedSeatLayout)

    BusSewaSDKTheme {

        Column {
            SearchTripView(searchTripViewModel, onDateSelected)
            SeatLayoutView(parsedSeatLayout.noOfColumn, parsedSeatLayout.seatLayout, listOf())
//            TripListView(tripList())
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusSewaSDKTheme {
        val searchTripState = SearchTripViewModel().searchTripState.collectAsState()
        Greeting(searchTripState.value) {
            /*if (it == null) {
                showDatePicker()
            } else {
                searchTripViewModel.setSearchDate(it)
            }*/
        }
    }
}