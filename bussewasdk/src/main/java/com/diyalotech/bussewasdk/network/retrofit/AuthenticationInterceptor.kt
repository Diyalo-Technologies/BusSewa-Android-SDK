package com.diyalotech.bussewasdk.network.retrofit

import com.diyalotech.bussewasdk.BASE_URL
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationInterceptor @Inject constructor() : Interceptor {
    @Volatile
    private var token: String? = null

    @Volatile
    private var host: String? = null

    private var packageName: String? = null

    fun setHost(host: String?) {
        this.host = host
    }

    fun setToken(token: String, packageName: String) {
        this.token = token
        this.packageName = packageName
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
        builder.addHeader("user", "sdkUser")
        val host = host

        if (original.header("No-Authentication") == null) {
            if (token == null || packageName == null) {
                throw RuntimeException("Session token should be defined for auth apis")
            } else {
                host?.let { builder.url(original.url.toString().replace(BASE_URL, it)) }
                builder.addHeader("Authorization", token!!)
                builder.addHeader("packageName", packageName!!)
            }
        }

        val request: Request = builder.build()
        return chain.proceed(request)
    }
}