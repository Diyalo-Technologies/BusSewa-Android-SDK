package com.diyalotech.bussewasdk.di

import com.diyalotech.bussewasdk.repo.SearchParamRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSearchParamRepo(): SearchParamRepository {
        return SearchParamRepository()
    }
}