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
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.BasicFields
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.DynamicTextFieldModel
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.TextFieldModel
import com.diyalotech.bussewasdk.ui.sharedcomposables.DynamicTextField
import com.diyalotech.bussewasdk.ui.sharedcomposables.ExposedDropDown
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
internal fun CustomerDynamicDetails(
    seatList: List<String>,
    details: List<PassengerDetail>,
    valueHolder: Map<String, List<DynamicTextFieldModel>>,
    onDynamicDetailsChanged: (seat: String, detailId: Int, value: String) -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            seatList.forEach { seat ->
                Text(
                    text = stringResource(id = R.string.passenger_detail) + " (Seat: $seat)",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface.copy(0.5f)
                )

                details.forEachIndexed { i, detail ->

                    val detailName = detail.detailName +
                            if (!detail.manditory) " ${stringResource(R.string.optional)}"
                            else ""

                    if (detail.inputType == InputType.TEXT_FIELD) {
                        DynamicTextField(
                            dynamicTextFieldModel = valueHolder[seat]?.get(i)!!,
                            label = detailName
                        ) {
                            onDynamicDetailsChanged(seat, detail.typeId, it)
                        }
                    }

                    if (detail.inputType == InputType.DROP_DOWN) {
                        ExposedDropDown(
                            valueHolder[seat]?.get(i)!!.value,
                            label = detailName,
                            dropDownItems = detail.valueList
                        ) {
                            onDynamicDetailsChanged(seat, detail.typeId, it)
                        }
                    }

                    if (detail.inputType == InputType.NUMBER_RANGE) {
                        DynamicTextField(
                            dynamicTextFieldModel = valueHolder[seat]?.get(i)!!,
                            label = detailName,
                            isNumberOnly = true
                        ) {
                            onDynamicDetailsChanged(seat, detail.typeId, it)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DynamicDetailsPreview() {
    BusSewaSDKTheme {
        val nameModelDynamic by remember {
            mutableStateOf(DynamicTextFieldModel(1))
        }
        val nameModel by remember {
            mutableStateOf(TextFieldModel())
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
        passengerDetail.add(PassengerDetail(5, "Age", InputType.NUMBER_RANGE))
        val seatList = mutableListOf("Vjbw", "qfk727")
        val boardingPoints = listOf(
            "k9006", "XlMK7b", "Bc67", "516GT", "2d44d4r"
        )

        Surface {
            CustomerDynamicDetails(
                seatList,
                passengerDetail,
                mapOf(
                    Pair(
                        "Vjbw", listOf(
                            nameModelDynamic,
                            nameModelDynamic,
                            nameModelDynamic,
                            nameModelDynamic,
                            nameModelDynamic
                        )
                    ),
                    Pair(
                        "qfk727", listOf(
                            nameModelDynamic,
                            nameModelDynamic,
                            nameModelDynamic,
                            nameModelDynamic,
                            nameModelDynamic
                        )
                    )
                ),
            ) { seat, id, value ->

            }
        }
    }
}