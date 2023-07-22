package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.DevicePreviews
import com.datikaa.charlatan.core.design.component.CmmSkill
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun OverviewRoute(
    characterId: Int,
    modifier: Modifier = Modifier,
    overviewViewModel: OverviewViewModel = koinViewModel { parametersOf(characterId) }
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    OverviewScreen(
        overviewUiState = uiState,
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
fun OverviewScreen(
    overviewUiState: OverviewUiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        val scrollState = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(state = scrollState),
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = overviewUiState.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing)) {
                Column(verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing)) {
                    CmmTitledCard(title = "Level") {
                        OutlinedCard(Modifier.width(IntrinsicSize.Min)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(-(3).dp),
                                modifier = Modifier
                                    .padding(all = 2.dp)
                                    .aspectRatio(1f, true)
                            ) {
                                Text(text = "", fontSize = 9.sp, maxLines = 1)
                                Text(text = "${overviewUiState.level}", fontSize = 16.sp)
                                Text(text = "", fontSize = 9.sp)
                            }
                        }
                    }
                    CmmTitledCard(title = "Abs") {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.align(Alignment.Center),
                        ) {
                            overviewUiState.abilities.forEach { attr ->
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
                                                text = attr.shortName,
                                                fontSize = 10.sp,
                                                maxLines = 1
                                            )
                                            Text(text = "${attr.calculatedScore}", fontSize = 16.sp)
                                            Text(text = "${attr.baseScore}", fontSize = 8.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    CmmTitledCard(title = "Prof") {
                        OutlinedCard(Modifier.width(IntrinsicSize.Min)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(-(3).dp),
                                modifier = Modifier
                                    .padding(all = 2.dp)
                                    .aspectRatio(1f, true)
                            ) {
                                Text(text = "", fontSize = 9.sp, maxLines = 1)
                                Text(text = "${overviewUiState.proficiency}", fontSize = 16.sp)
                                Text(text = "", fontSize = 9.sp)
                            }
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
                ) {
                    CmmTitledCard(
                        title = "Skills",
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
                            modifier = Modifier.width(IntrinsicSize.Max),
                        ) {
                            overviewUiState.skills.forEach { skill ->
                                CmmSkill(
                                    name = "${skill.name} (${skill.baseName})",
                                    score = skill.score,
                                    proficiency = skill.proficiency,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewOverviewScreen() {
    OverviewScreen(
        overviewUiState = OverviewUiState(
            level = 7,
            name = "Azmoday",
            proficiency = 3,
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
            ),
            skills = listOf(
                OverviewUiState.UiSkill(
                    name = "Strength", baseName = "Str", score = 12, proficiency = false,
                ),
                OverviewUiState.UiSkill(
                    name = "Slight of Hand", baseName = "Cha", score = 12, proficiency = true,
                ),
            ),
        ),
    )
}
