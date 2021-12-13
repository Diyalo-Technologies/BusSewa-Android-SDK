package com.diyalotech.bussewasdk.ui.locationlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

class LocationListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BusSewaSDKTheme {
                Surface() {
                    SearLocationView() {
                        finish()
                    }
                }
            }
        }
    }
}