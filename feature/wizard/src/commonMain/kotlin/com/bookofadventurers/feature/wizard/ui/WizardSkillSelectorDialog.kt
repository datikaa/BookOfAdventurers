package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WizardSkillSelectorDialog(
    dialogState: WizardUiState.Dialog,
    selectClass: WizardUiState.ClassItem,
    onDismissRequest: () -> Unit,
    toggleProficiency: (Long, Int) -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(
                Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth()
            ) {
                val remainingSelectionCount =
                    selectClass.selectableCount - selectClass.selectableSkillProficiencyModifiers.count { it.selected }
                Text(
                    text = "Select skill proficiencies",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                Text(
                    text = "Available: $remainingSelectionCount",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(all = 10.dp),
                )
                val scrollState = rememberScrollState()
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        BookOfAdventurersTheme.dimensions.cardSpacing
                    ),
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .weight(1f, false),
                ) {
                    selectClass.selectableSkillProficiencyModifiers.forEach {
                        val interactionSource = remember { MutableInteractionSource() }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CircleShape)
                                .clickable(
                                    enabled = it.editable,
                                    onClick = { toggleProficiency(dialogState.classId, it.id) },
                                    indication = LocalIndication.current,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = it.name,
                                modifier = Modifier.alpha(if (it.editable) 1f else 0.38f),
                            )
                            Checkbox(
                                checked = it.selected,
                                enabled = it.editable,
                                interactionSource = interactionSource,
                                onCheckedChange = null,
                            )
                        }
                    }
                }
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.align(Alignment.End),
                ) {
                    Text(text = "Close")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWizardSkillSelectorDialog() {
    WizardSkillSelectorDialog(
        dialogState = WizardUiState.Dialog(
            classId = 8945,
            selectableCount = 3
        ),
        selectClass = WizardUiState.ClassItem(
            id = 1876,
            name = "Bard",
            selectableCount = 3,
            savingThrowProficiencyModifiers = listOf(),
            selectableSkillProficiencyModifiers = listOf(
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "strength",
                    selected = false,
                    editable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = false,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "strength",
                    selected = false,
                    editable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = false,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "strength",
                    selected = false,
                    editable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = false,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "strength",
                    selected = false,
                    editable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = false,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = true,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "strength",
                    selected = false,
                    editable = false,
                ),

                ),
            selected = false
        ),
        onDismissRequest = {},
        toggleProficiency = { _, _ -> },
    )
}
