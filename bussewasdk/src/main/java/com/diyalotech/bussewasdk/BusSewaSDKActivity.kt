package com.diyalotech.bussewasdk

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_CLIENT_INFO
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_MESSAGE
import com.diyalotech.bussewasdk.sdkbuilders.BusSewaClient
import com.diyalotech.bussewasdk.ui.BusSewaSDKApp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusSewaSDKActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val client = getClient()!!
        setContent {
            BusSewaSDKTheme(client.busTheme) {
                BusSewaSDKApp()
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
}
