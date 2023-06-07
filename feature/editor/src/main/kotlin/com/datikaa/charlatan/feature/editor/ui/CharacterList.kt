package com.datikaa.charlatan.feature.editor.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.core.domain.Character

@Composable
fun CharacterList(
    uiState: CharactersUiState,
    addCharacter: (String) -> Unit,
    selectCharacter: (Character) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
    ) {
        CmmTitledCard(
            title = "Add character",
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column {
                var charNameText by remember { mutableStateOf("") }
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
                Button(onClick = addName) {
                    Text("Add")
                }
            }
        }

        CmmTitledCard(
            title = "Characters",
            modifier = Modifier.fillMaxWidth(),
        ) {
            val scrollState = rememberScrollState()
            Column(modifier = Modifier.verticalScroll(scrollState)) {
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
                Character(
                    id = 5614,
                    name = "Jesi",
                    level = 6108,
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            )
        ),
        addCharacter = { /* nothing */ },
        selectCharacter = { /* nothing */ },
    )
}