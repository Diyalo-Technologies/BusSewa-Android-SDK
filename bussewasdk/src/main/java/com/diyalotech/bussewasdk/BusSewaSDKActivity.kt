package com.diyalotech.bussewasdk

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_CLIENT_INFO
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_MESSAGE
import com.diyalotech.bussewasdk.sdkbuilders.BusSewaClient
import com.diyalotech.bussewasdk.ui.HomeView
import com.diyalotech.bussewasdk.ui.locationlist.launchLocationList
import com.diyalotech.bussewasdk.ui.models.LocationType
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.ui.triplist.launchTripListActivity
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.*

@AndroidEntryPoint
class BusSewaSDKActivity : AppCompatActivity() {

    private val searchTripViewModel: SearchTripViewModel by viewModels()
    private lateinit var picker: MaterialDatePicker<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val client = getClient()!!
        setContent {
            BusSewaSDKTheme(client.busTheme) {
                ProvideWindowInsets {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        color = MaterialTheme.colors.background
                    ) {
                        HomeView(
                            searchTripViewModel,
                            ::onDateSelected,
                            ::onLocationClicked,
                            ::onSearchClicked
                        )
                    }
                }
            }
        }

        buildDatePicker()
    }

    private fun buildDatePicker() {
        picker = MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .build()
            )
            .setSelection(Date().time)
            .build()

        picker.addOnPositiveButtonClickListener {
            val instant = Instant.fromEpochMilliseconds(it)
            val date = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
            onDateSelected(date)
        }
    }

    private fun onDateSelected(date: LocalDate?) {

        if (date != null) {
            searchTripViewModel.setSearchDate(date)
        } else {
            if (!picker.isAdded) {
                picker.show(supportFragmentManager, "Date Picker")
            }

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

    private fun onLocationClicked(locationType: LocationType) {
        searchTripViewModel.setLocationSelectionMode(locationType)
        launchLocationList(this)
    }

    private fun onSearchClicked() {
        launchTripListActivity(this)
    }
}
