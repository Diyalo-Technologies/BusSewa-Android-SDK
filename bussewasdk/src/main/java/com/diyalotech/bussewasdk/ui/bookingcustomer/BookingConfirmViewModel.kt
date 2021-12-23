package com.diyalotech.bussewasdk.ui.bookingcustomer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class TextFieldModel(
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)

data class DynamicTextFieldModel(
    val id: Int,
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)

data class BoardingPoint(
    val id: String,
    val display: String
)

class BookingConfirmViewModel : ViewModel() {

    var nameState = mutableStateOf(TextFieldModel())
    var emailState = mutableStateOf(TextFieldModel())
    var mobileState = mutableStateOf(TextFieldModel())
    var boardingPointState = mutableStateOf(TextFieldModel())

    private var _boardingPoints = MutableStateFlow(
        listOf(
            BoardingPoint("", "Tetsdfsdf"),
            BoardingPoint("", "Tetsdfsdf"),
            BoardingPoint("", "Tetsdfsdf"),
            BoardingPoint("", "Tetsdfsdf"),
            BoardingPoint("", "Tetsdfsdf"),
            BoardingPoint("", "Tetsdfsdf"),
        )
    )
    val boardingPoints: StateFlow<List<BoardingPoint>> = _boardingPoints


}