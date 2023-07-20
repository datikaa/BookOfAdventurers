package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.component.CmmSkill
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun OverviewRoute(
    characterId: Int,
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: OverviewViewModel = koinViewModel { parametersOf(characterId) }
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    OverviewScreen(
        overviewUiState = uiState,
        clear = overviewViewModel::clearDb,
        navigateToCharacter = { navigate("characters") },
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
fun OverviewScreen(
    overviewUiState: OverviewUiState,
    clear: () -> Unit,
    navigateToCharacter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing)) {
            Column {
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
                CmmTitledCard(title = "Attrs") {
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
                                        Text(text = attr.shortName, fontSize = 10.sp, maxLines = 1)
                                        Text(text = "${attr.calculatedScore}", fontSize = 16.sp)
                                        Text(text = "${attr.baseScore}", fontSize = 8.sp)
                                    }
                                }
                            }
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
                                name = skill.name,
                                score = skill.score,
                                proficiency = skill.proficiency,
                            )
                        }
                    }
                }
                CmmTitledCard(title = overviewUiState.name) {
                    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur commodo, lectus nec mollis tempus, tellus sapien ultrices nisl, commodo volutpat felis ipsum eget massa.")
                }
            }

        }
        Row {
            Button(onClick = { navigateToCharacter() }) {
                Text("Characters")
            }
            Button(onClick = { clear() }) {
                Text("Clear DB")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OverviewScreen(
        overviewUiState = OverviewUiState(
            level = 7,
            name = "Azmoday",
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
                    name = "Strength", score = 12, proficiency = false,
                ),
                OverviewUiState.UiSkill(
                    name = "Slight of Hand", score = 12, proficiency = true,
                ),
            ),
        ),
        clear = {},
        navigateToCharacter = {},
    )
}
