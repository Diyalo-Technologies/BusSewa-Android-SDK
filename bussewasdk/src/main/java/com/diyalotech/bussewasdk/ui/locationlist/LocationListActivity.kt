package com.diyalotech.bussewasdk.ui.locationlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.ProvideWindowInsets
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

fun launchLocationList(context: Context) {
    val intent = Intent(context, LocationListActivity::class.java)
    context.startActivity(intent)
}

@AndroidEntryPoint
class LocationListActivity : ComponentActivity() {

    private val searchLocationViewModel: SearchLocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BusSewaSDKTheme {
                ProvideWindowInsets {
                    Surface {
                        SearchLocationView(
                            searchLocationViewModel,
                            onBackPressed = { finish() },
                            onSelected = {
                                searchLocationViewModel.saveSelectedLocation(it)
                                finish()
                            }
                        )
                    }
                }
            }
        }
    }
}