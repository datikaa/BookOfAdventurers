package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun OverviewScreen(
    overviewViewModel: OverviewViewModel = koinViewModel()
) {
    CmmTitledCard(title = "Title") {
        Text(text = "Some text will go here")
    }
}

@Preview
@Composable
private fun Preview() {
    OverviewScreen()
}
