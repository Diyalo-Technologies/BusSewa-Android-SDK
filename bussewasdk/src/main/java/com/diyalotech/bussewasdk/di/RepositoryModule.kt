package com.diyalotech.bussewasdk.di

import com.diyalotech.bussewasdk.repo.DataStoreRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSearchParamRepo(): DataStoreRepository {
        return DataStoreRepository()
    }
}