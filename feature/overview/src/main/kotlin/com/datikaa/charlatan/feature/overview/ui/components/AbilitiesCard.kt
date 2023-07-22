package com.datikaa.charlatan.feature.overview.ui.components

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
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.feature.overview.ui.OverviewUiState

@Composable
fun AbilitiesCard(
    abilities: List<OverviewUiState.UiAbility>,
    modifier: Modifier = Modifier,
) {
    CmmTitledCard(
        title = "Abs",
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Center),
        ) {
            abilities.forEach { uiAbility ->
                Column {
                    OutlinedCard(Modifier.width(IntrinsicSize.Min)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(-(3).dp),
                            modifier = Modifier
                                .padding(all = 2.dp)
                                .aspectRatio(1f, true)
                        ) {
                            Text(
                                text = uiAbility.shortName,
                                fontSize = 10.sp,
                                maxLines = 1
                            )
                            Text(text = "${uiAbility.calculatedScore}", fontSize = 16.sp)
                            Text(text = "${uiAbility.baseScore}", fontSize = 8.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAbilitiesCard() {
    AbilitiesCard(
        abilities = listOf(
            OverviewUiState.UiAbility(
                name = "Strength", shortName = "Str", baseScore = 11, calculatedScore = 0,
            ),
            OverviewUiState.UiAbility(
                name = "Charisma", shortName = "Cha", baseScore = 14, calculatedScore = 3,
            ),
            OverviewUiState.UiAbility(
                name = "Dexterity", shortName = "Dex", baseScore = 8, calculatedScore = -1,
            ),
        )
    )
}
