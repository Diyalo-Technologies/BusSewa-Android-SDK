package com.diyalotech.bussewasdk.network.retrofit

import android.util.Log
import com.diyalotech.bussewasdk.BusSewaSDKActivity
import com.diyalotech.bussewasdk.LOG_BUS
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UnauthorizedInterceptor @Inject constructor(private val appContext: BusSewaSDKActivity) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401) {
            Log.e(LOG_BUS, "Unauthorized, invalid client credentials provided.")
            appContext.finish()
        }
        return response
    }
}