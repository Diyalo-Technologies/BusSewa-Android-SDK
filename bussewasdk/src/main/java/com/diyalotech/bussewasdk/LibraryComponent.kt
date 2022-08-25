package com.diyalotech.bussewasdk

import android.content.Context
import com.diyalotech.bussewasdk.di.NetworkModule
import com.diyalotech.bussewasdk.di.RepositoryModule
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.AuthenticationInterceptor
import com.diyalotech.bussewasdk.ui.bookingcustomer.BookingConfirmViewModel
import com.diyalotech.bussewasdk.ui.di.ViewModelModule
import com.diyalotech.bussewasdk.ui.locationlist.SearchLocationViewModel
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatsViewModel
import com.diyalotech.bussewasdk.ui.triplist.TripListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ViewModelModule::class, RepositoryModule::class])
@Singleton
internal interface LibraryComponent {

    @Component.Builder
    interface Builder {
        fun build(): LibraryComponent

        @BindsInstance
        fun activity(trainSDKActivity: BusSewaSDKActivity): Builder
    }

    fun inject(context: BusSewaSDKActivity)

    fun getSearchTripVM(): SearchTripViewModel

    fun getTripListVM(): TripListViewModel

    fun getSearchLocationVM(): SearchLocationViewModel

    fun getSelectSeatsVM(): SelectSeatsViewModel

    fun getBookingCustomerVM(): BookingConfirmViewModel

}