package com.diyalotech.bussewasdk

import android.content.Context
import com.diyalotech.bussewasdk.di.NetworkModule
import com.diyalotech.bussewasdk.di.RepositoryModule
import com.diyalotech.bussewasdk.ui.bookingcustomer.BookingConfirmViewModel
import com.diyalotech.bussewasdk.ui.di.ViewModelModule
import com.diyalotech.bussewasdk.ui.locationlist.SearchLocationViewModel
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatsViewModel
import com.diyalotech.bussewasdk.ui.triplist.TripListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ViewModelModule::class, RepositoryModule::class])
@Singleton
interface LibraryComponent {

    @Component.Builder
    interface Builder {
        fun build(): LibraryComponent
    }

    fun inject(context: Context)

    fun getSearchTripVM(): SearchTripViewModel

    fun getTripListVM(): TripListViewModel

    fun getSearchLocationVM(): SearchLocationViewModel

    fun getSelectSeatsVM(): SelectSeatsViewModel

    fun getBookingCustomerVM(): BookingConfirmViewModel

}