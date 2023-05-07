package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun OverviewScreen(
    overviewViewModel: OverviewViewModel = koinViewModel()
) {
    Card {
        Column {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    text = "Box header",
                )
            }
            Text(
                modifier = Modifier.padding(all = 8.dp),
                text = LipSum
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OverviewScreen()
}

private val LipSum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
        " Integer gravida ex ullamcorper," +
        " interdum lacus ac, bibendum ipsum."