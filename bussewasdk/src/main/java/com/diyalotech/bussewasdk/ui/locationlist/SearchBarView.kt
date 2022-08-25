package com.diyalotech.bussewasdk.ui.locationlist

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
internal fun SearchBarView(
    text: String,
    requestFocus: Boolean = false,
    onValueChanged: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    Row(Modifier.statusBarsPadding()) {
        Icon(
            Icons.Outlined.ArrowBack,
            "",
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .clickable {
                    onBackPressed()
                }
                .padding(8.dp)

        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            BasicTextField(
                text,
                onValueChanged,
                Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
            )
            if (text.isEmpty())
                Text(
                    "Search locations",
                    color = MaterialTheme.colors.onSurface.copy(0.5f),
                )
        }

        if (text.isNotEmpty())
            Icon(
                Icons.Outlined.Close,
                "",
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .clickable {
                        onValueChanged("")
                    }
            )
    }

    DisposableEffect(Unit) {
        if (requestFocus)
            focusRequester.requestFocus()

        onDispose {
            focusRequester.freeFocus()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    BusSewaSDKTheme {
        Surface {
            SearchBarView("", true, {}) {

            }
        }
    }
}