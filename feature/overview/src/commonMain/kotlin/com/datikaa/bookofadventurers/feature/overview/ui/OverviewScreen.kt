package com.datikaa.bookofadventurers.feature.overview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.feature.overview.ui.components.AbilitiesCard
import com.datikaa.bookofadventurers.feature.overview.ui.components.ProficiencyCard
import com.datikaa.bookofadventurers.feature.overview.ui.components.SavingThrowsCard
import com.datikaa.bookofadventurers.feature.overview.ui.components.SkillsCard
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun OverviewRoute(
    navigateBack: () -> Unit,
    characterId: Int,
    modifier: Modifier = Modifier,
    overviewViewModel: OverviewViewModel = koinViewModel { parametersOf(characterId) }
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    OverviewScreen(
        overviewUiState = uiState,
        navigateBack = navigateBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    navigateBack: () -> Unit,
    overviewUiState: OverviewUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = overviewUiState.name)
                        Text(
                            text = "Level ${overviewUiState.level} - ${overviewUiState.className} - ${overviewUiState.backgroundName}",
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "backIcon")
                    }
                },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(state = scrollState)
                .padding(BookOfAdventurersTheme.dimensions.screenPadding),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                Column(verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                    AbilitiesCard(abilities = overviewUiState.abilities)
                    ProficiencyCard(proficiency = overviewUiState.proficiency)
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                ) {
                    SavingThrowsCard(savingThrow = overviewUiState.savingThrows)
                    SkillsCard(skills = overviewUiState.skills)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOverviewScreen() {
    OverviewScreen(
        overviewUiState = OverviewUiState(
            level = 7,
            name = "Azmoday",
            backgroundName = "Acolyte",
            className = "Wizard",
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
            backgrounds = listOf(
                OverviewUiState.UiBackground(
                    name = "Acolyte"
                )
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
        navigateBack = {},
    )
}
