package com.datikaa.charlatan.feature.editor.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.datikaa.charlatan.core.design.component.CmmModifierEditor
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import java.util.concurrent.Flow

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharacterEditor(
    uiState: CharactersUiState,
    openCharacterSheet: (Character) -> Unit,
    decreaseAbility: (Character, Ability) -> Unit,
    increaseAbility: (Character, Ability) -> Unit,
    decreaseLevel: (Character) -> Unit,
    increaseLevel: (Character) -> Unit,
    addModifier: (Character, Int) -> Unit,
    back: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedCharacter = uiState.selectedCharacter
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
    ) {
        CmmTitledCard(
            title = selectedCharacter?.name ?: "No selected character",
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (selectedCharacter != null) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
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
                        Divider(modifier = Modifier.fillMaxWidth())
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
                    horizontalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
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
                Character(
                    id = 3524,
                    name = "Jessic",
                    level = 6111,
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
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
            selectedCharacter = Character(
                id = 6497, name = "Justin", level = 797, abilityList = listOf(
                    Ability.Strength(value = 10),
                    Ability.Charisma(value = 10),
                    Ability.Constitution(value = 10),
                    Ability.Dexterity(value = 10),
                    Ability.Wisdom(value = 10),
                    Ability.Intelligence(value = 10),
                ), modifiers = listOf()
            ),
            characters = listOf(
                Character(
                    id = 6497,
                    name = "Justin",
                    level = 797,
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
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
