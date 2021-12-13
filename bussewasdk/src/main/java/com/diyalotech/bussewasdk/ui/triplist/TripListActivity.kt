package com.diyalotech.bussewasdk.ui.triplist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

fun launchTripListActivity(context: Context) {
    val intent = Intent(context, TripListActivity::class.java)
    context.startActivity(intent)
}


class TripListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

        }
    }
}