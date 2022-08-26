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
import com.diyalotech.bussewasdk.sdkbuilders.*
import com.diyalotech.bussewasdk.test.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.ui.theme.createDefaultTheme

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
        val client = BusSewaClient(
            clientId = "admin",
            clientSecret = "admin",
            environment = BusSewaSdkEnv.TEST,
            busTheme = BusTheme(
                primary = R.color.purple_200,
                primaryVariant = R.color.purple_200,
                secondary = R.color.purple_200,
                busThemeDarkLight = BusThemeDarkLight.LIGHT
            )
        )
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