package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.DynamicTextFieldModel
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.TextFieldModel

@Composable
internal fun SimpleTextField(
    textFieldModel: TextFieldModel,
    label: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    prefix: String = "",
    isNumberOnly: Boolean = false,
    isLastField: Boolean = false,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier
    ) {
        OutlinedTextField(
            value = textFieldModel.value,
            modifier = Modifier.fillMaxWidth(),
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
        ErrorMessageTextField(textFieldModel.isError, textFieldModel.errorMessage)
    }
}

@Composable
internal fun DynamicTextField(
    dynamicTextFieldModel: DynamicTextFieldModel,
    label: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    isNumberOnly: Boolean = false,
    onValueChange: (String) -> Unit
) {
    Column {
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
        ErrorMessageTextField(dynamicTextFieldModel.isError, dynamicTextFieldModel.errorMessage)
    }

}