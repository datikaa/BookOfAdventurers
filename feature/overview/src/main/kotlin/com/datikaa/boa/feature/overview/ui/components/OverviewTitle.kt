package com.datikaa.boa.feature.overview.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun OverviewTitle(
    name: String,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = CircleShape,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Preview
@Composable
private fun PreviewOverviewTitle() {
    OverviewTitle(name = "Barbarianna")
}
