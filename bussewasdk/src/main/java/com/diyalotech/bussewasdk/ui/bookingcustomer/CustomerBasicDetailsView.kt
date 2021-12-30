package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
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
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.BasicFields
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.TextFieldModel
import com.diyalotech.bussewasdk.ui.sharedcomposables.ExposedDropDown
import com.diyalotech.bussewasdk.ui.sharedcomposables.SimpleListItem
import com.diyalotech.bussewasdk.ui.sharedcomposables.SimpleTextField
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.navigationBarsPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomerBasicDetailsView(
    boardingPointModel: TextFieldModel,
    emailModel: TextFieldModel,
    mobileModel: TextFieldModel,
    boardingPoints: List<String>,
    nameModel: TextFieldModel? = null,
    onValueChanged: (field: BasicFields, value: String) -> Unit
) {

    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {

        Column(Modifier.padding(8.dp)) {

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
                onItemSelected = { onValueChanged(BasicFields.BOARDING_POINT, it) }
            )

            nameModel?.let { name ->
                SimpleTextField(
                    textFieldModel = name,
                    label = stringResource(id = R.string.full_name),
                    icon = Icons.Outlined.AccountCircle,
                    onValueChange = { onValueChanged(BasicFields.NAME, it) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            SimpleTextField(
                textFieldModel = emailModel,
                label = stringResource(id = R.string.email_opt),
                icon = Icons.Outlined.Email,
                onValueChange = { onValueChanged(BasicFields.EMAIL, it) },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            SimpleTextField(
                textFieldModel = mobileModel,
                label = stringResource(id = R.string.mobile_number),
                icon = Icons.Outlined.Phone,
                onValueChange = { onValueChanged(BasicFields.PHONE, it) },
                prefix = "+977-",
                isNumberOnly = true,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun BoardingPointView(
    title: String,
    boardingPoints: List<String>,
    onItemClicked: (String) -> Unit
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
            SimpleListItem(it) {
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
            mutableStateOf(TextFieldModel())
        }

        val boardingPoints = listOf(
            "pNYuyXxN", "20My39r", "MfBT", "KW7P8yy9", "wmKpIYji"
        )

        Surface {
            CustomerBasicDetailsView(
                nameModel,
                nameModel,
                nameModel,
                boardingPoints,
                nameModel
            ) { a, b ->

            }
        }
    }
}
