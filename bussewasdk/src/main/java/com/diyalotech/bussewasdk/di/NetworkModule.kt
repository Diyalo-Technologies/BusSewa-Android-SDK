package com.diyalotech.bussewasdk.di

import com.diyalotech.bussewasdk.BASE_URL
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        // Potential dependencies of this type
    ): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor {
                val request: Request = it.request().newBuilder()
                    .addHeader("Authorization", "Basic bW9iaWxlQDEyMzptb2JpbGVAMTIz")
                    .addHeader("user", "mobile")
                    .build()
                it.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}