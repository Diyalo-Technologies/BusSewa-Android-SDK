package com.diyalotech.bussewasdk.ui.locationlist

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun SearchBarView(text: String, onValueChanged: (String) -> Unit, onBackPressed: () -> Unit) {
    Row {
        Icon(
            Icons.Outlined.ArrowBack,
            "",
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onBackPressed()
                }
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
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.body2
            )
            if (text.isEmpty())
                Text(
                    "Search locations",
                    color = MaterialTheme.colors.onSurface.copy(0.5f),
                    fontSize = 14.sp
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
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    BusSewaSDKTheme {
        Surface {
            SearchBarView("",{}) {

            }
        }
    }
}