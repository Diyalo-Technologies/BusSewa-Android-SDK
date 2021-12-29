package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.TextFieldModel

@Composable
fun SimpleDropDownField(
    textFieldModel: TextFieldModel,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    prefix: String = "",
    onValueChange: (String) -> Unit,
    onClicked: () -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    if(interactionSource.collectIsPressedAsState().value) {
        onClicked()
    }

    val temp =
        if (prefix.isNotEmpty()) prefix + " " + textFieldModel.value else textFieldModel.value

    OutlinedTextField(
        value = temp,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    leadingIcon,
                    contentDescription = ""
                )
            }
        },
        trailingIcon = {
            Icon(
                Icons.Outlined.ArrowDropDown,
                contentDescription = ""
            )
        },
        isError = textFieldModel.isError,
        readOnly = true,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        interactionSource = interactionSource
    )

}