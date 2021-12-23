package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.network.dto.InputType
import com.diyalotech.bussewasdk.network.dto.PassengerDetail
import com.diyalotech.bussewasdk.ui.sharedcomposables.DynamicTextField
import com.diyalotech.bussewasdk.ui.sharedcomposables.ExposedDropDown
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun CustomerDynamicDetails(
    title: String,
    details: List<PassengerDetail>,
    valueHolder: List<DynamicTextFieldModel>,
    onValueChanged: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .shadow(6.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface.copy(0.5f)
        )

        details.forEachIndexed { i, detail ->
            if (detail.inputType == InputType.TEXT_FIELD) {
                DynamicTextField(
                    dynamicTextFieldModel = valueHolder[i],
                    label = detail.detail
                ) {
                    onValueChanged(detail.id, it)
                }
            }

            if (detail.inputType == InputType.DROP_DOWN) {
                ExposedDropDown(
                    valueHolder[i].value,
                    label = detail.detail,
                    dropDownItems = detail.values
                ) {
                    onValueChanged(detail.id, it)
                }
            }

            if (detail.inputType == InputType.NUMBER_RANGE) {
                DynamicTextField(
                    dynamicTextFieldModel = valueHolder[i],
                    label = detail.detail,
                    isNumberOnly = true
                ) {
                    onValueChanged(detail.id, it)
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DynamicDetailsPreview() {
    BusSewaSDKTheme {
        val nameModel by remember {
            mutableStateOf(DynamicTextFieldModel(1, "", false, ""))
        }

        val passengerDetail = mutableListOf<PassengerDetail>()
        passengerDetail.add(PassengerDetail(1, "Name", InputType.TEXT_FIELD))
        passengerDetail.add(PassengerDetail(2, "Phone", InputType.TEXT_FIELD))
        passengerDetail.add(
            PassengerDetail(
                3,
                "Gender",
                InputType.DROP_DOWN,
                values = listOf("MALE, FEMALE")
            )
        )
        passengerDetail.add(PassengerDetail(4, "Name", InputType.TEXT_FIELD))
        passengerDetail.add(PassengerDetail(4, "Age", InputType.NUMBER_RANGE))

        val boardingPoints = listOf(
            BoardingPoint("Nepal", "1"),
            BoardingPoint("Nepal", "1"),
            BoardingPoint("Nepal", "1")
        )
        Surface {
            CustomerDynamicDetails(
                "Passenger Details (Seat: 12)",
                passengerDetail,
                listOf(nameModel, nameModel, nameModel, nameModel, nameModel),
            ) { id, value ->

            }
        }
    }
}