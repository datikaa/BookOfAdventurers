package com.datikaa.charlatan.feature.character.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.feature.character.domain.CmmCharacter
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: CharactersScreenViewModel = koinViewModel()
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    CharactersListView(
        uiState = uiState,
        addCharacter = overviewViewModel::addCharacter,
        openCharacter = { navigate("overview/$it") },
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
private fun CharactersListView(
    uiState: CharactersUiState,
    addCharacter: (String) -> Unit,
    openCharacter: (Int) -> Unit,
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
            Column {
                uiState.characters.forEach { character ->
                    OutlinedButton(
                        onClick = { openCharacter(character.id) },
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
    CharactersListView(
        uiState = CharactersUiState(
            selectedCharacter = null,
            characters = listOf(
                CmmCharacter(0, "Azmoday")
            )
        ),
        addCharacter = { /* nothing */ },
        openCharacter= { /* nothing */ },
    )
}