package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.network.dto.InputType
import com.diyalotech.bussewasdk.network.dto.PassengerDetail
import com.diyalotech.bussewasdk.network.dto.SeatLayout
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.BasicFields
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.DynamicTextFieldModel
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.TextFieldModel
import com.diyalotech.bussewasdk.ui.sharedcomposables.DynamicTextField
import com.diyalotech.bussewasdk.ui.sharedcomposables.ExposedDropDown
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun CustomerDynamicDetails(
    mobileModel: TextFieldModel,
    emailModel: TextFieldModel,
    boardingPointModel: TextFieldModel,
    seatList: List<String>,
    boardingPoints: List<String>,
    details: List<PassengerDetail>,
    valueHolder: Map<String, List<DynamicTextFieldModel>>,
    nameModel: TextFieldModel? = null,
    onBasicDetailChanged: (BasicFields, String) -> Unit,
    onDynamicDetailsChanged: (seat: String, detailId: Int, value: String) -> Unit
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .shadow(6.dp)
            .verticalScroll(scrollState)
    ) {

        CustomerBasicDetailsView(
            boardingPointModel,
            emailModel,
            mobileModel,
            boardingPoints,
            onValueChanged = onBasicDetailChanged
        )

        Spacer(modifier = Modifier.height(16.dp))
        seatList.forEachIndexed { seatIndex, seat ->
            Text(
                text = stringResource(id = R.string.passenger_detail) + " (Seat: $seat)",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface.copy(0.5f)
            )

            details.forEachIndexed { i, detail ->
                if (detail.inputType == InputType.TEXT_FIELD) {
                    DynamicTextField(
                        dynamicTextFieldModel = valueHolder[seat]?.get(i)!!,
                        label = detail.detailName
                    ) {
                        onDynamicDetailsChanged(seat, detail.tyepId, it)
                    }
                }

                if (detail.inputType == InputType.DROP_DOWN) {
                    ExposedDropDown(
                        valueHolder[seat]?.get(i)!!.value,
                        label = detail.detailName,
                        dropDownItems = detail.valueList
                    ) {
                        onDynamicDetailsChanged(seat, detail.tyepId, it)
                    }
                }

                if (detail.inputType == InputType.NUMBER_RANGE) {
                    DynamicTextField(
                        dynamicTextFieldModel = valueHolder[seat]?.get(i)!!,
                        label = detail.detailName,
                        isNumberOnly = true
                    ) {
                        onDynamicDetailsChanged(seat, detail.tyepId, it)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DynamicDetailsPreview() {
    BusSewaSDKTheme {
        val nameModelDynamic by remember {
            mutableStateOf(DynamicTextFieldModel(1, "", false, ""))
        }
        val nameModel by remember {
            mutableStateOf(TextFieldModel("", false, ""))
        }

        val passengerDetail = mutableListOf<PassengerDetail>()
        passengerDetail.add(PassengerDetail(1, "Name", InputType.TEXT_FIELD))
        passengerDetail.add(PassengerDetail(2, "Phone", InputType.TEXT_FIELD))
        passengerDetail.add(
            PassengerDetail(
                3,
                "Gender",
                InputType.DROP_DOWN,
                valueList = listOf("MALE, FEMALE")
            )
        )
        passengerDetail.add(PassengerDetail(4, "Name", InputType.TEXT_FIELD))
        passengerDetail.add(PassengerDetail(4, "Age", InputType.NUMBER_RANGE))
        val seatList = mutableListOf("Vjbw", "qfk727", "go11q80n", "3x1", "5Gd5Ko6w")
        val boardingPoints = listOf(
            "k9006", "XlMK7b", "Bc67", "516GT", "2d44d4r"
        )

        Surface {
            CustomerDynamicDetails(
                nameModel,
                nameModel,
                nameModel,
                seatList,
                boardingPoints,
                passengerDetail,
                mapOf(
                    Pair("",listOf(
                        nameModelDynamic,
                        nameModelDynamic,
                        nameModelDynamic,
                        nameModelDynamic,
                        nameModelDynamic
                    )),
                    Pair("",listOf(
                        nameModelDynamic,
                        nameModelDynamic,
                        nameModelDynamic,
                        nameModelDynamic,
                        nameModelDynamic
                    ))
                ),
                nameModel,
                onBasicDetailChanged = { a, b -> }
            ) { seat, id, value ->

            }
        }
    }
}