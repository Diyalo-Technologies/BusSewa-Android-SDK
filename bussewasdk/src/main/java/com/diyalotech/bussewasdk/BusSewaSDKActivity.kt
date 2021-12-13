package com.diyalotech.bussewasdk

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.diyalotech.bussewasdk.network.SeatLayoutDTO
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_CLIENT_INFO
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_MESSAGE
import com.diyalotech.bussewasdk.sdkbuilders.BusSewaClient
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripView
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.seatlayout.testString
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import kotlinx.datetime.LocalDate

class BusSewaSDKActivity : AppCompatActivity() {

    val searchTripViewModel: SearchTripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val client = getClient()!!
        setContent {
            BusSewaSDKTheme(client.busTheme) {
                ProvideWindowInsets {

                    val insets = LocalWindowInsets.current

                    // A surface container using the 'background' color from the theme
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.statusBarsPadding()
                    ) {
                        Greeting(searchTripViewModel) {
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

    private fun getClient(): BusSewaClient? {
        val client = intent.getParcelableExtra<BusSewaClient>(BUS_SDK_CLIENT_INFO)

        if (client == null) {
            val data = Intent()
            val message = "No client info provided..."
            data.putExtra(BUS_SDK_MESSAGE, message)
            setResult(RESULT_OK, data)
            finish()
        }
        return client
    }
}

@Composable
fun Greeting(searchTripViewModel: SearchTripViewModel, onDateSelected: (LocalDate?) -> Unit) {
    val parsedSeatLayout = Gson().fromJson(testString, SeatLayoutDTO::class.java)
    println(parsedSeatLayout)

    var searchText by remember { mutableStateOf("") }

    BusSewaSDKTheme {

        Column {
            val searchTripModel = searchTripViewModel.searchTripState.collectAsState()
                .value
            SearchTripView(searchTripModel, onDateSelected, {searchTripViewModel.swapLocation()})
//            SeatLayoutView(parsedSeatLayout.noOfColumn, parsedSeatLayout.seatLayout, listOf())
//            TripListView(tripList())
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusSewaSDKTheme {
        val searchTripViewModel = SearchTripViewModel()
        Greeting(searchTripViewModel) {
            /*if (it == null) {
                showDatePicker()
            } else {
                searchTripViewModel.setSearchDate(it)
            }*/
        }
    }
}