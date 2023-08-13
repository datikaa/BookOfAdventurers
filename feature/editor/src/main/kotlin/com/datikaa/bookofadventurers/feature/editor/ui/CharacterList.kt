package com.datikaa.bookofadventurers.feature.editor.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.datikaa.bookofadventurers.core.domain.BoaClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterList(
    uiState: CharactersUiState,
    addCharacter: (String, Int) -> Unit,
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
            Column(verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                var charNameText by remember { mutableStateOf("") }
                var classListExpanded by remember { mutableStateOf(false) }
                var selectedClassId by remember { mutableStateOf<Int?>(null) }
                val buttonEnabled by remember {
                    derivedStateOf {
                        charNameText.isNotBlank() && selectedClassId != null
                    }
                }
                val addName = {
                    addCharacter(charNameText, selectedClassId!!)
                    selectedClassId = null
                    charNameText = ""
                }
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    value = charNameText,
                    onValueChange = { charNameText = it },
                    label = { Text("Name") })
                ExposedDropdownMenuBox(expanded = classListExpanded, onExpandedChange = {
                    classListExpanded = !classListExpanded
                }) {
                    OutlinedTextField(
                        value = uiState.classes.firstOrNull { it.id == selectedClassId }?.name
                            ?: "Select class",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = classListExpanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = classListExpanded,
                        onDismissRequest = { classListExpanded = false }) {
                        uiState.classes.forEach { classItem ->
                            DropdownMenuItem(text = { Text(text = classItem.name) },
                                onClick = {
                                    selectedClassId = classItem.id
                                    classListExpanded = false
                                }
                            )
                        }
                    }
                }
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
                    boaClass = BoaClass(1, "Wizard", emptyList()),
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
            classes = emptyList(),
            modifiers = emptyList(),
        ),
        addCharacter = { _, _ -> /* nothing */ },
        selectCharacter = { /* nothing */ },
    )
}
