package com.datikaa.bookofadventurers.feature.editor.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.datikaa.bookofadventurers.core.design.component.CmmTitledCard
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass

@Composable
fun CharacterList(
    uiState: CharactersUiState,
    selectCharacter: (BoaCharacter) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
    ) {
        CmmTitledCard(
            title = "Characters",
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column {
                uiState.characters.forEach { character ->
                    OutlinedButton(
                        onClick = { selectCharacter(character) },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = character.name)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CharacterList(
        uiState = CharactersUiState(
            selectedCharacter = null,
            characters = listOf(
                BoaCharacter(
                    id = 5614,
                    name = "Jesi",
                    level = 6108,
                    characterBackground = Background(1, "Acolyte", "", "", "", emptyList()),
                    characterClass = CharacterClass(1, "Wizard", emptyList(), emptyList()),
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
            classes = emptyList(),
            modifiers = emptyList(),
        ),
        selectCharacter = { /* nothing */ },
    )
}
