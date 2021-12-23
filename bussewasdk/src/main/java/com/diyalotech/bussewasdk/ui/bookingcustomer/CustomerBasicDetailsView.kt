package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.network.dto.InputType
import com.diyalotech.bussewasdk.network.dto.PassengerDetail
import com.diyalotech.bussewasdk.ui.sharedcomposables.*
import com.diyalotech.bussewasdk.ui.theme.Shapes
import com.google.accompanist.insets.navigationBarsPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomerDetailsView(
    boardingPointModel: TextFieldModel,
    nameModel: TextFieldModel?,
    emailModel: TextFieldModel,
    mobileModel: TextFieldModel,
    boardingPoints: List<String>,
    onBoardingPointClicked: (String) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {


    Column {
        TopAppBar(
            title = stringResource(id = R.string.booking_confirmation),
            showBack = true,
            backAction = onBackPressed
        )

        Column {
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .shadow(6.dp)
            ) {

                Text(
                    text = "Contact person details.",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface.copy(0.5f)
                )

                ExposedDropDown(
                    selectedItem = boardingPointModel.value,
                    label = stringResource(id = R.string.boarding_point),
                    dropDownItems = boardingPoints,
                    leadingIcon = Icons.Default.AddLocation,
                    onItemSelected = {}
                )

                nameModel?.let {
                    SimpleTextField(
                        textFieldModel = nameModel,
                        label = stringResource(id = R.string.full_name),
                        icon = Icons.Outlined.AccountCircle,
                        onValueChange = {},
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                    )
                }

                SimpleTextField(
                    textFieldModel = emailModel,
                    label = stringResource(id = R.string.email),
                    icon = Icons.Outlined.Email,
                    onValueChange = {},
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                )

                SimpleTextField(
                    textFieldModel = mobileModel,
                    label = stringResource(id = R.string.mobile_number),
                    icon = Icons.Outlined.Phone,
                    onValueChange = {},
                    prefix = "+977-",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                )
            }


            //for test
            val model = DynamicTextFieldModel(1, "", false, "")
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
            Spacer(modifier = Modifier.height(16.dp))
            CustomerDynamicDetails(
                title = "Passenger Details (Seat: A12)",
                details = passengerDetail,
                valueHolder = listOf(model, model, model, model, model)
            ) { id, value ->

            }
        }
    }
}

@Composable
fun BoardingPointView(
    title: String,
    boardingPoints: List<BoardingPoint>,
    onItemClicked: (BoardingPoint) -> Unit
) {
    Column(
        Modifier
            .navigationBarsPadding()
            .padding(bottom = 8.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp),
            color = MaterialTheme.colors.onSurface.copy(0.5f)
        )
        boardingPoints.forEach {
            SimpleListItem(it.display) {
                println("clicked")
                onItemClicked(it)
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomerDetailsPreview() {
    BusSewaSDKTheme {
        val nameModel by remember {
            mutableStateOf(TextFieldModel("", false, ""))
        }

        val boardingPoints = listOf(
            "pNYuyXxN", "20My39r", "MfBT", "KW7P8yy9", "wmKpIYji"
        )

        Surface {
            CustomerDetailsView(nameModel, null, nameModel, nameModel, boardingPoints)
        }
    }
}
