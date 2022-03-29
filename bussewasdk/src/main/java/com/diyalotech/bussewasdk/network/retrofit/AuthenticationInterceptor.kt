package com.diyalotech.bussewasdk.network.retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {

    private var token: String? = null

    public fun setToken(token: String) {
        this.token = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
        builder.addHeader("user","mobile")

        if (original.header("No-Authentication") == null) {
            if (token == null) {
                throw RuntimeException("Session token should be defined for auth apis")
            } else {
                builder.addHeader("Authorization", token!!)
            }
        }

        val request: Request = builder.build()
        return chain.proceed(request)
    }
}