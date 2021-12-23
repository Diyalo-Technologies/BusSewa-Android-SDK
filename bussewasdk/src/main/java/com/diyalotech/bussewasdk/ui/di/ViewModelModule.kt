package com.diyalotech.bussewasdk.ui.di

import com.diyalotech.bussewasdk.repo.LocationRepository
import com.diyalotech.bussewasdk.repo.SearchParamRepository
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
    fun provideSearchTripVM(searchParamRepository: SearchParamRepository): SearchTripViewModel {
        return SearchTripViewModel(searchParamRepository)
    }

    @Provides
    fun provideTripListVM(
        searchParamRepository: SearchParamRepository,
        tripRepository: TripRepository
    ): TripListViewModel {
        return TripListViewModel(searchParamRepository, tripRepository)
    }

    @Provides
    @Singleton
    fun provideSearchLocationVM(
        locationRepository: LocationRepository,
        searchParamRepo: SearchParamRepository
    ): SearchLocationViewModel {
        return SearchLocationViewModel(locationRepository, searchParamRepo)
    }

    @Provides
    fun provideSeatsSelectVM(
        tripRepository: TripRepository,
        searchParamRepo: SearchParamRepository
    ): SelectSeatsViewModel {
        return SelectSeatsViewModel(tripRepository, searchParamRepo)
    }

    @Provides
    fun provideBookingConfirmViewModel(): BookingConfirmViewModel {
        return BookingConfirmViewModel()
    }
}