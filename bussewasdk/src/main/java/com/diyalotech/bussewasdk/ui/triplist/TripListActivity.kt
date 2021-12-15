package com.diyalotech.bussewasdk.ui.triplist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import com.diyalotech.bussewasdk.ui.locationlist.SearchLocationView
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

fun launchTripListActivity(context: Context) {
    val intent = Intent(context, TripListActivity::class.java)
    context.startActivity(intent)
}

@AndroidEntryPoint
class TripListActivity : ComponentActivity() {

    private val tripListViewModel: TripListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BusSewaSDKTheme {
                ProvideWindowInsets {
                    Surface {

                        TripListView(tripListViewModel)
                    }
                }
            }
        }
    }
}