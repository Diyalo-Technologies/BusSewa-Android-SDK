package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.network.dto.MultiPrice
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.BasicFields
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.PassengerPriceDetail
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.PriceFieldModel
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.TextFieldModel
import com.diyalotech.bussewasdk.ui.sharedcomposables.PriceExposedDropDown
import com.diyalotech.bussewasdk.ui.sharedcomposables.SimpleTextField
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun CustomerMultiPriceDetails(
    mobileModel: TextFieldModel,
    emailModel: TextFieldModel,
    boardingPointModel: TextFieldModel,
    boardingPoints: List<String>,
    seatList: List<String>,
    priceList: List<MultiPrice>,
    passengerPriceDetails: Map<String, PassengerPriceDetail>,
    onBasicDetailsChanged: (field: BasicFields, value: String) -> Unit,
    onNameChanged: (seat: String, String) -> Unit,
    onPriceSelected: (seat: String, MultiPrice) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .verticalScroll(scrollState)
    ) {
        CustomerBasicDetailsView(
            boardingPointModel,
            emailModel,
            mobileModel,
            boardingPoints,
            onValueChanged = onBasicDetailsChanged
        )
        Card(
            elevation = 4.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Column(Modifier.padding(8.dp)) {
                seatList.forEachIndexed { i, seat ->

                    Text(
                        text = stringResource(id = R.string.passenger_detail) + " (Seat: $seat)",
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface.copy(0.5f)
                    )

                    SimpleTextField(
                        passengerPriceDetails[seat]?.textFieldModel!!,
                        label = stringResource(id = R.string.full_name),
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { onNameChanged(seat, it) }
                    )

                    PriceExposedDropDown(
                        selectedItem = passengerPriceDetails[seat]?.priceFieldModel!!,
                        dropDownItems = priceList
                    ) {
                        onPriceSelected(seat, it)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MultiPricePreview() {
    BusSewaSDKTheme {
        Surface {
            val multiPriceList = listOf(
                MultiPrice(1, "Adult", 10.0, 1000.0),
                MultiPrice(2, "Child", 10.0, 1000.0),
            )
            val nameHolderDynamic = mutableMapOf(
                Pair("81XnSiL", PassengerPriceDetail(PriceFieldModel(), TextFieldModel())),
                Pair("3vsxuiG", PassengerPriceDetail(PriceFieldModel(), TextFieldModel())),
            )
            val nameHolder = TextFieldModel()
            val boardingPoints = listOf(
                "81XnSiL", "3vsxuiG", "k6g7", "66LAb", "40hV"
            )
            val seatList = listOf(
                "81XnSiL", "3vsxuiG"
            )

            CustomerMultiPriceDetails(
                nameHolder,
                nameHolder,
                nameHolder,
                boardingPoints,
                seatList,
                multiPriceList,
                nameHolderDynamic,
                { a, b -> },
                { a, b -> },
                { a, b -> }
            )
        }
    }
}
