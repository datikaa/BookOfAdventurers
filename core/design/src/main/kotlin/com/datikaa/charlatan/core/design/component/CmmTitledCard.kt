package com.datikaa.charlatan.core.design.component

import androidx.compose.foundation.layout.Box
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
import com.datikaa.charlatan.core.design.LipSum

@Composable
fun CmmTitledCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(modifier = modifier) {
        Column {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    text = title,
                )
            }
            Box(modifier = Modifier.padding(all = 8.dp)) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CmmTitledCard(title = "Title") {
        Text(text = LipSum)
    }
}
