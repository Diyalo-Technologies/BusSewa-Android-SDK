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
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.ui.sharedcomposables.Chip
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.utils.localDateNow
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus


@Composable
fun DateSelectionView(
    label: String,
    date: String,
    onDateSelected: (date: LocalDate?) -> Unit
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .clickable {
            onDateSelected.invoke(null)
        }
        .padding(8.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colors.primary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp)

        ) {
            Icon(Icons.Outlined.CalendarToday, "", tint = MaterialTheme.colors.primary)
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        ) {
            Text(label, fontSize = 12.sp)
            Text(
                if (date.isEmpty()) "yyyy-MM-dd" else date,
                color = if (date.isEmpty()) MaterialTheme.colors.onSurface.copy(0.5f) else MaterialTheme.colors.primary,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Chip(modifier = Modifier.align(Alignment.CenterVertically), {
            onDateSelected(localDateNow())
        }) {
            Text("Today", color = MaterialTheme.colors.onSurface.copy(0.55f))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Chip(modifier = Modifier.align(Alignment.CenterVertically), {
            onDateSelected(localDateNow().plus(DatePeriod(days = 1)))
        }) {
            Text("Tomorrow", color = MaterialTheme.colors.onSurface.copy(0.55f))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DateSelectionPreview() {
    BusSewaSDKTheme {
        Surface {
            DateSelectionView(label = "Date", date = "", onDateSelected = {})
        }
    }
}