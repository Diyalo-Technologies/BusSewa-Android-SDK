package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.DynamicTextFieldModel
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.TextFieldModel

@Composable
fun SimpleTextField(
    textFieldModel: TextFieldModel,
    label: String,
    icon: ImageVector? = null,
    prefix: String = "",
    modifier: Modifier = Modifier,
    isNumberOnly: Boolean = false,
    isLastField: Boolean = false,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = textFieldModel.value,
        modifier = modifier,
        isError = textFieldModel.isError,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (prefix.isNotEmpty()) PrefixTransformation(prefix) else VisualTransformation.None,
        leadingIcon = icon?.let {
            { Icon(it, contentDescription = "") }
        },
        singleLine = true,
        keyboardOptions = if (isNumberOnly)
            KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        else
            KeyboardOptions.Default,
    )
}

@Composable
fun DynamicTextField(
    dynamicTextFieldModel: DynamicTextFieldModel,
    label: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    isNumberOnly: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = dynamicTextFieldModel.value,
        leadingIcon = icon?.let {
            { Icon(it, contentDescription = "") }
        },
        isError = dynamicTextFieldModel.isError,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth(),
        keyboardOptions = if (isNumberOnly)
            KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        else
            KeyboardOptions.Default
    )

}