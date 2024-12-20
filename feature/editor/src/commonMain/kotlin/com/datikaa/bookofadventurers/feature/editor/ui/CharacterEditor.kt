package com.datikaa.bookofadventurers.feature.editor.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.datikaa.bookofadventurers.core.design.component.CmmModifierEditor
import com.datikaa.bookofadventurers.core.design.component.CmmTitledCard
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharacterEditor(
    uiState: CharactersUiState,
    openCharacterSheet: (BoaCharacter) -> Unit,
    decreaseAbility: (BoaCharacter, Ability) -> Unit,
    increaseAbility: (BoaCharacter, Ability) -> Unit,
    decreaseLevel: (BoaCharacter) -> Unit,
    increaseLevel: (BoaCharacter) -> Unit,
    addModifier: (BoaCharacter, Int) -> Unit,
    back: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedCharacter = uiState.selectedCharacter
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
    ) {
        CmmTitledCard(
            title = selectedCharacter?.name ?: "No selected character",
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (selectedCharacter != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(IntrinsicSize.Max)
                    ) {
                        CmmModifierEditor(
                            text = "Level: ${selectedCharacter.level}",
                            decrease = { decreaseLevel(selectedCharacter) },
                            increase = { increaseLevel(selectedCharacter) },
                            modifier = Modifier.fillMaxWidth(),
                        )
                        HorizontalDivider(modifier = Modifier.fillMaxWidth())
                        selectedCharacter.abilityList.forEach { ability ->
                            CmmModifierEditor(
                                text = "${ability::class.simpleName} ${ability.value}",
                                decrease = { decreaseAbility(selectedCharacter, ability) },
                                increase = { increaseAbility(selectedCharacter, ability) },
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    ) {
                        Button(onClick = { openCharacterSheet(uiState.selectedCharacter) }) {
                            Text(text = "Character Sheet")
                        }
                        Button(onClick = back) {
                            Text(text = "Back")
                        }
                    }
                }
            }
        }
        if (selectedCharacter != null) {
            CmmTitledCard(
                title = "Modifiers",
                modifier = Modifier.fillMaxWidth(),
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                ) {
                    uiState.modifiers.forEach {
                        FilterChip(
                            selected = it.selected,
                            onClick = { addModifier(uiState.selectedCharacter, it.id) },
                            label = { Text(text = it.name) },
                            shape = CircleShape,
                            trailingIcon = {
                                Icon(
                                    imageVector = if (it.selected) Icons.Rounded.Check else Icons.Rounded.Close,
                                    contentDescription = "check"
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharacterEditorUnselectedPreview() {
    CharacterEditor(
        uiState = CharactersUiState(
            selectedCharacter = null,
            characters = listOf(
                BoaCharacter(
                    id = 3524,
                    name = "Jessic",
                    level = 6111,
                    characterBackground = Background(1, "Acolyte", "", "", "", emptyList()),
                    characterClass = CharacterClass(1, "Wizard", emptyList(), emptyList()),
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
            classes = emptyList(),
            modifiers = emptyList(),
        ),
        openCharacterSheet = { },
        back = { },
        decreaseAbility = { _, _ -> },
        increaseAbility = { _, _ -> },
        decreaseLevel = { _ -> },
        increaseLevel = { _ -> },
        addModifier = { _, _ -> },
    )
}

@Preview
@Composable
private fun CharacterEditorSelectedPreview() {
    CharacterEditor(
        uiState = CharactersUiState(
            selectedCharacter = BoaCharacter(
                id = 6497, name = "Justin",
                characterBackground = Background(1, "Acolyte", "", "", "", emptyList()),
                characterClass = CharacterClass(1, "Wizard", emptyList(), emptyList()),
                level = 797,
                abilityList = listOf(
                    Ability.Strength(value = 10),
                    Ability.Charisma(value = 10),
                    Ability.Constitution(value = 10),
                    Ability.Dexterity(value = 10),
                    Ability.Wisdom(value = 10),
                    Ability.Intelligence(value = 10),
                ), modifiers = listOf()
            ),
            characters = listOf(
                BoaCharacter(
                    id = 6497,
                    name = "Justin",
                    level = 797,
                    characterBackground = Background(1, "Acolyte", "", "", "", emptyList()),
                    characterClass = CharacterClass(1, "Wizard", emptyList(), emptyList()),
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
            classes = listOf(),
            modifiers = listOf(
                CharactersUiState.Modifier(0, "Proficiency modifier", true),
                CharactersUiState.Modifier(0, "Proficiency modifier", false)
            ),
        ),
        openCharacterSheet = { },
        back = { },
        decreaseAbility = { _, _ -> },
        increaseAbility = { _, _ -> },
        decreaseLevel = { _ -> },
        increaseLevel = { _ -> },
        addModifier = { _, _ -> },
    )
}
