package com.diyalotech.bussewasdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.diyalotech.bussewasdk.network.SeatLayoutDTO
import com.diyalotech.bussewasdk.network.tripList
import com.diyalotech.bussewasdk.ui.TripListView
import com.diyalotech.bussewasdk.ui.TripView
import com.diyalotech.bussewasdk.ui.seatlayout.SeatLayoutView
import com.diyalotech.bussewasdk.ui.seatlayout.testString
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.gson.Gson

class BusSewaSDKActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            BusSewaSDKTheme {
                ProvideWindowInsets {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        Greeting("Android")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val parsedSeatLayout = Gson().fromJson(testString, SeatLayoutDTO::class.java)
    println(parsedSeatLayout)

    BusSewaSDKTheme {

        Column {
            //SeatLayoutView(parsedSeatLayout.noOfColumn, parsedSeatLayout.seatLayout, listOf())
            TripListView(tripList())
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusSewaSDKTheme {
        Greeting("Android")
    }
}