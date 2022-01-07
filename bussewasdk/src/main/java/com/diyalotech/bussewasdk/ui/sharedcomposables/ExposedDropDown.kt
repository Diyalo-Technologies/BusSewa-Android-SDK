package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
internal fun ExposedDropDown(
    selectedItem: String,
    dropDownItems: List<String>,
    label: String? = null,
    isError: Boolean = false,
    errorMessage: String = "",
    leadingIcon: ImageVector? = null,
    onItemSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val interactionSource = remember { MutableInteractionSource() }

    if (interactionSource.collectIsPressedAsState().value) {
        expanded = !expanded
    }

    Column {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = label?.let { { Text(label) } },
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        it,
                        "icon"
                    )
                }
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.ArrowDropDown,
                    "drop down"
                )
            },
            isError = isError,
            interactionSource = interactionSource
        )
        ErrorMessageTextField(isError, errorMessage, Modifier.padding(start = 4.dp))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onItemSelected(item)
                }) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview
@Composable
fun DropDownPreview() {
    BusSewaSDKTheme {
        Surface {
            ExposedDropDown("Test", listOf("Hello", "Test", "Nepal", "What"), "label") {

            }
        }
    }
}