package com.diyalotech.bussewasdk.ui.searchtrip

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun LocationView(
    text: String = "",
    label: String,
    leadingIcon: ImageVector? = null,
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .clickable {
            onClick()
        }
        .padding(8.dp)
    ) {
        if (leadingIcon != null) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.primary.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(8.dp)

            ) {
                Icon(leadingIcon, "", tint = MaterialTheme.colors.primary)
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        ) {
            Text(
                text = label,
                color = MaterialTheme.colors.onSurface.copy(0.6f),
                fontSize = 12.sp
            )
            Text(
                text = if (text.isEmpty()) "Kathmandu" else text,
                modifier = Modifier,
                color = if (text.isEmpty()) MaterialTheme.colors.onSurface.copy(0.5f) else MaterialTheme.colors.primary
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LocationPreview() {
    BusSewaSDKTheme {
        Surface {
            LocationView("", "Source", Icons.Outlined.Place) {
            }
        }
    }
}
