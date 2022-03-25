package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SimpleListItem(text: String, onItemClicked: (() -> Unit)? = null) {
    Text(
        text,
        Modifier
            .fillMaxWidth()
            .clickable(onItemClicked != null) { onItemClicked?.invoke() }
            .padding(horizontal = 16.dp, vertical = 16.dp)
    )
}