package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.utils.toNPRString

@Composable
fun SelectedSeatsBottomBar(
    selectedSeats: List<String>,
    totalPrice: Double,
    modifier: Modifier = Modifier,
    onBookClicked: () -> Unit
) {

    val text = if (selectedSeats.isEmpty()) "" else
        selectedSeats.reduceIndexed { i, text, comb ->
            text + (if (i == 0) "" else ", ") + comb
        }

    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(bottom = 16.dp)
            .navigationBarsPadding()
    ) {

        Divider(Modifier.padding(bottom = 16.dp))
        AnimatedVisibility(
            selectedSeats.isNotEmpty(),
            Modifier.fillMaxWidth(),
        ) {
            Row(Modifier.padding(start = 20.dp, end = 20.dp, bottom = 8.dp)) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        stringResource(id = R.string.selected_seats),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .animateContentSize()
                    )
                }

                Column {
                    Text(
                        stringResource(id = R.string.total_price),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primary
                    )
                    Text(totalPrice.toNPRString())
                }
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onBookClicked,
            enabled = selectedSeats.isNotEmpty()
        ) {
            Text(text = stringResource(id = R.string.book_seats))
        }

    }
}

@Preview
@Composable
fun SelectedSeatsBottomBarPreview() {
    SelectedSeatsBottomBar(
        listOf(
            "12", "14", "16"
        ),
        1000.0
    ) {

    }
}