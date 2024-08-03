package com.bookofadventurers.feature.background.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddNewBackgroundDialog(
    modifiers: List<BackgroundsUiState.Modifier>,
    addNewBackground: (String, String, String, String, List<Long>) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var modifiersDialogVisible by remember {
        mutableStateOf(false)
    }
    var selectedModifiers by remember { mutableStateOf(emptyList<BackgroundsUiState.Modifier>()) }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            val scrollState = rememberScrollState()
            Column(
                verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(all = 10.dp)
                    .fillMaxWidth()
            ) {
                var name by remember { mutableStateOf("") }
                var featureTitle by remember { mutableStateOf("") }
                var featureDescription by remember { mutableStateOf("") }
                var suggestedCharacteristics by remember { mutableStateOf("") }
                Text(
                    text = "Add new character background",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") },
                    maxLines = 1,
                )
                OutlinedTextField(
                    value = featureTitle,
                    onValueChange = { featureTitle = it },
                    label = { Text(text = "Feature title") },
                    maxLines = 1,
                )
                OutlinedTextField(
                    value = featureDescription,
                    onValueChange = { featureDescription = it },
                    label = { Text(text = "Feature description") },
                    maxLines = 20,
                )
                OutlinedTextField(
                    value = suggestedCharacteristics,
                    onValueChange = { suggestedCharacteristics = it },
                    label = { Text(text = "Suggested characteristics") },
                    maxLines = 20,
                )
                HorizontalDivider()
                selectedModifiers.forEach {
                    ProficiencyItem(proficiencyModifier = it)
                }
                TextButton(onClick = { modifiersDialogVisible = true }) {
                    Text(text = "SELECT MODIFIERS")
                }
                HorizontalDivider()
                ElevatedButton(
                    onClick = {
                        addNewBackground(
                            name,
                            featureTitle,
                            featureDescription,
                            suggestedCharacteristics,
                            selectedModifiers.map { it.id.toLong() },
                        )
                        onDismissRequest()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add")
                }
            }
        }
    }
    if (modifiersDialogVisible) {
        ModifierSelectorDialog(
            modifiers = modifiers,
            selectedModifiers = selectedModifiers,
            onDismissRequest = { modifiersDialogVisible = false },
            onModifierCheckedChange = { modifierItem, checked ->
                selectedModifiers = if (checked) {
                    selectedModifiers.plus(modifierItem)
                } else {
                    selectedModifiers.minus(modifierItem)
                }
            }
        )
    }
}

@Preview
@Composable
fun AddNewBackgroundDialogPreview() {
    AddNewBackgroundDialog(
        modifiers = emptyList(),
        addNewBackground = { _, _, _, _, _ -> },
        onDismissRequest = {},
    )
}
