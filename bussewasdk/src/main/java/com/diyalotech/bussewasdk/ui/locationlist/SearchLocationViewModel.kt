package com.diyalotech.bussewasdk.ui.locationlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchLocationViewModel: ViewModel() {

    private var _searchString by mutableStateOf("")
    val searchString: String = _searchString

    fun onSearchChanged(string: String) {
        _searchString = string
    }

}