package com.diyalotech.bussewasdk

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.AuthenticationInterceptor
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_CLIENT_INFO
import com.diyalotech.bussewasdk.sdkbuilders.BUS_SDK_MESSAGE
import com.diyalotech.bussewasdk.sdkbuilders.BusSewaClient
import com.diyalotech.bussewasdk.sdkbuilders.BusSewaSdkEnv
import com.diyalotech.bussewasdk.ui.BusSewaSDKApp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import okhttp3.Credentials
import retrofit2.Retrofit
import javax.inject.Inject

class BusSewaSDKActivity : AppCompatActivity() {

    private lateinit var component: LibraryComponent
    @Inject
    internal lateinit var authenticationInterceptor: AuthenticationInterceptor

    override fun onCreate(savedInstanceState: Bundle?) {
        component = DaggerLibraryComponent.builder()
            .activity(this)
            .build()
        component.inject(this)

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val client = getClient()!!

        val token = Credentials.basic(client.clientId, client.clientSecret)
        authenticationInterceptor.setToken(token, applicationContext.packageName)
        authenticationInterceptor.setHost(
            if(client.environment== BusSewaSdkEnv.PROD) LIVE_URL else null
        )
        setContent {
            BusSewaSDKTheme(client.busTheme) {
                BusSewaSDKApp(component)
            }
        }
    }

    private fun getClient(): BusSewaClient? {
        val client = intent.getParcelableExtra<BusSewaClient>(BUS_SDK_CLIENT_INFO)

        if (client == null) {
            val data = Intent()
            val message = "No client info provided..."
            data.putExtra(BUS_SDK_MESSAGE, message)
            setResult(RESULT_CANCELED, data)
            finish()
            return null
        }
        return client
    }
}
