package com.diyalotech.bussewasdk.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.BusSewaSDKActivity
import com.diyalotech.bussewasdk.sdkbuilders.*
import com.diyalotech.bussewasdk.test.ui.theme.BusSewaSDKTheme

class MainActivity : ComponentActivity() {

    val busSdkLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ::onBusSdkResult
    )

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

    private fun launch() {
        val intent = Intent(this, BusSewaSDKActivity::class.java)
        val client = BusSewaClient(clientId = "")
        intent.putExtra(BUS_SDK_CLIENT_INFO, client)
        busSdkLauncher.launch(intent)
    }

    private fun onBusSdkResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == Activity.RESULT_OK) {

            val data =
                (activityResult.data?.getSerializableExtra(BUS_SDK_RESPONSE) as HashMap<String, Any>?)

            println("RESULT_MAP: $data")

            println(data?.get(BUS_SDK_REQ_ID))
            println(data?.get(BUS_SDK_AMOUNT))

            val props = (data?.get(BUS_SDK_PROPERTIES) as HashMap<String, String>?)
            println(props?.get(BUS_SDK_TRIP_ID))
            println(props?.get(BUS_SDK_TICKET))

            //iterate on properties
            props?.forEach { map ->
                println("${map.key} - ${map.value}")
            }
        }
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