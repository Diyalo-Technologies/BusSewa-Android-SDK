package com.diyalotech.bussewasdk.ui.di

import com.diyalotech.bussewasdk.repo.BookingRepository
import com.diyalotech.bussewasdk.repo.LocationRepository
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.repo.TripRepository
import com.diyalotech.bussewasdk.ui.bookingcustomer.BookingConfirmViewModel
import com.diyalotech.bussewasdk.ui.locationlist.SearchLocationViewModel
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatsViewModel
import com.diyalotech.bussewasdk.ui.triplist.TripListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    fun provideSearchTripVM(dataStoreRepository: DataStoreRepository): SearchTripViewModel {
        return SearchTripViewModel(dataStoreRepository)
    }

    @Provides
    fun provideTripListVM(
        dataStoreRepository: DataStoreRepository,
        tripRepository: TripRepository
    ): TripListViewModel {
        return TripListViewModel(dataStoreRepository, tripRepository)
    }

    @Provides
    @Singleton
    fun provideSearchLocationVM(
        locationRepository: LocationRepository,
        dataStoreRepo: DataStoreRepository
    ): SearchLocationViewModel {
        return SearchLocationViewModel(locationRepository, dataStoreRepo)
    }

    @Provides
    fun provideSeatsSelectVM(
        tripRepository: TripRepository,
        dataStoreRepo: DataStoreRepository
    ): SelectSeatsViewModel {
        return SelectSeatsViewModel(tripRepository, dataStoreRepo)
    }

    @Provides
    fun provideBookingConfirmViewModel(dataStoreRepo: DataStoreRepository, bookingRepo: BookingRepository): BookingConfirmViewModel {
        return BookingConfirmViewModel(dataStoreRepo, bookingRepo)
    }
}