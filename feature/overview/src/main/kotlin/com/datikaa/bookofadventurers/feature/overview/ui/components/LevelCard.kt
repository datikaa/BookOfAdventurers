package com.datikaa.bookofadventurers.feature.overview.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.datikaa.bookofadventurers.core.design.component.CmmTitledCard

@Composable
internal fun LevelCard(
    level: Int,
    modifier: Modifier = Modifier,
) {
    CmmTitledCard(
        title = "Level",
        modifier = modifier,
    ) {
        OutlinedCard(Modifier.width(IntrinsicSize.Min)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(-(3).dp),
                modifier = Modifier
                    .padding(all = 2.dp)
                    .aspectRatio(1f, true)
            ) {
                Text(text = "", fontSize = 9.sp, maxLines = 1)
                Text(text = "$level", fontSize = 16.sp)
                Text(text = "", fontSize = 9.sp)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLevelCard() {
    LevelCard(level = 8)
}