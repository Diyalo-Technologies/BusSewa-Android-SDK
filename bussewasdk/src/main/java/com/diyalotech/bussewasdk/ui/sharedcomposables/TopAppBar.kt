package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun TopAppBar(
    title: String,
    subtitle: String = "",
    showBack: Boolean,
    elevation: Dp = 4.dp,
    backAction: () -> Unit = {}
) {
    Row(
        Modifier
            .heightIn(56.dp)
            .fillMaxWidth()
            .shadow(elevation)
            .background(MaterialTheme.colors.surface)
            .statusBarsPadding()
    ) {

        if (showBack)
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "",
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable { backAction?.invoke() }
                    .size(40.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
            )
            if (subtitle.isNotEmpty())
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface.copy(0.8f),
                )
        }
    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    BusSewaSDKTheme {
        Surface {
            TopAppBar(title = "BusSewa", showBack = true)
        }
    }
}