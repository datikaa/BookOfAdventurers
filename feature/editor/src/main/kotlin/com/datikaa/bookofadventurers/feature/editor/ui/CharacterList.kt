package com.datikaa.bookofadventurers.feature.editor.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.datikaa.bookofadventurers.core.design.component.CmmTitledCard
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.core.domain.BoaCharacter

@Composable
fun CharacterList(
    uiState: CharactersUiState,
    addCharacter: (String) -> Unit,
    selectCharacter: (BoaCharacter) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
    ) {
        CmmTitledCard(
            title = "Add character",
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column {
                var charNameText by remember { mutableStateOf("") }
                val buttonEnabled by remember { derivedStateOf { charNameText.isNotBlank() } }
                val addName = {
                    addCharacter(charNameText)
                    charNameText = ""
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = charNameText,
                    onValueChange = { charNameText = it },
                    label = { Text("Name") }
                )
                Button(
                    enabled = buttonEnabled,
                    onClick = addName,
                ) {
                    Text("Add")
                }
            }
        }

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
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
            modifiers = emptyList(),
        ),
        addCharacter = { /* nothing */ },
        selectCharacter = { /* nothing */ },
    )
}
