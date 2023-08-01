package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.DevicePreviews
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.feature.overview.ui.components.AbilitiesCard
import com.datikaa.charlatan.feature.overview.ui.components.LevelCard
import com.datikaa.charlatan.feature.overview.ui.components.ProficiencyCard
import com.datikaa.charlatan.feature.overview.ui.components.SavingThrowsCard
import com.datikaa.charlatan.feature.overview.ui.components.SkillsCard
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
            Row(horizontalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing)) {
                Column(verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing)) {
                    LevelCard(level = overviewUiState.level)
                    AbilitiesCard(abilities = overviewUiState.abilities)
                    ProficiencyCard(proficiency = overviewUiState.proficiency)
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
                ) {
                    SavingThrowsCard(savingThrow = overviewUiState.savingThrows)
                    SkillsCard(skills = overviewUiState.skills)
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
            savingThrows = listOf(
                OverviewUiState.UiSavingThrow(
                    name = "Strength", baseName = "Str", score = 12, proficiency = false,
                ),
                OverviewUiState.UiSavingThrow(
                    name = "Slight of Hand", baseName = "Cha", score = 12, proficiency = true,
                ),
            ),
        ),
    )
}
