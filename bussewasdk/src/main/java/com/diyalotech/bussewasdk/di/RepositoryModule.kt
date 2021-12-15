package com.diyalotech.bussewasdk.di

import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.repo.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTripRepository(apiService: ApiService): LocationRepository {
        return LocationRepository(apiService)
    }
}