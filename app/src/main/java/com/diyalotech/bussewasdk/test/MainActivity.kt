package com.diyalotech.bussewasdk.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.BusSewaSDKActivity
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_CLIENT_INFO
import com.diyalotech.bussewasdk.sdkbuilders.BusSewaClient
import com.diyalotech.bussewasdk.test.ui.theme.BusSewaSDKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusSewaSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                    Button(onClick = { launch() }) {
                        Text("LCiclksjdlfkjsldf")
                    }
                }
            }
        }

        launch()
    }

    fun launch() {
        val intent = Intent(this, BusSewaSDKActivity::class.java)
        val client = BusSewaClient(clientId = "")
        intent.putExtra(BUS_SDK_CLIENT_INFO, client)
        startActivity(intent)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusSewaSDKTheme {
        Greeting("Android")
    }
}