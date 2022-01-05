package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme


@Composable
internal fun ErrorMessage(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
internal fun ErrorMessageTextField(isError: Boolean, text: String) {
    if (isError) {
        Text(
            text = text,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
internal fun ErrorMessageDialog(text: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        buttons = {
            Box(Modifier.padding(16.dp)) {
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(text = stringResource(id = R.string.okay))
                }
            }
        },
        title = {
            Text("Error!", style = MaterialTheme.typography.h6)
        },
        text = {
            Text(text = text)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ErrorMessagePreview() {
    BusSewaSDKTheme {
        Surface {
            Card {
                ErrorMessageDialog("test") {

                }
            }
        }
    }
}