package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WizardClassComponent(
    classItem: WizardUiState.ClassItem,
    selectClass: (Long) -> Unit,
    openProficiencyDialog: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        border = if (classItem.selected) BorderStroke(2.dp, Color.Green) else null,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
            modifier = Modifier
                .clickable {
                    selectClass(classItem.id)
                }
                .fillMaxWidth()
                .padding(all = 10.dp),
        ) {
            Text(
                text = classItem.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Text(
                text = "Proficiencies",
                style = MaterialTheme.typography.headlineSmall,
            )
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Column(verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                classItem.savingThrowProficiencyModifiers.forEach {
                    ProficiencyItem(it)
                }
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Column(
                verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                modifier = Modifier.fillMaxWidth()
            ) {
                val selectedSkillProficiencyModifiers =
                    classItem.selectableSkillProficiencyModifiers.filter { it.selected }
                val remainingSelectionCount =
                    classItem.selectableCount - selectedSkillProficiencyModifiers.size
                repeat(remainingSelectionCount) {
                    ProficiencyItem(
                        WizardUiState.Modifier(
                            id = 0,
                            name = "Not selected",
                            selected = false,
                            editable = true
                        )
                    )
                }
                selectedSkillProficiencyModifiers.forEach {
                    ProficiencyItem(it)
                }
                TextButton(
                    onClick = { openProficiencyDialog(classItem.id) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = if (remainingSelectionCount != 0) "Select $remainingSelectionCount skill proficiency" else "Edit selection")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWizardClassComponent() {
    WizardClassComponent(
        classItem = WizardUiState.ClassItem(
            id = 0,
            name = "Barbarian",
            selectableCount = 2,
            savingThrowProficiencyModifiers = listOf(
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = true,
                    editable = false,
                ),
            ),
            selectableSkillProficiencyModifiers = listOf(
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = false,
                    editable = false,
                ),
            ),
            selected = true,
        ),
        selectClass = {},
        openProficiencyDialog = {},
        modifier = Modifier,
    )
}

@Preview
@Composable
fun PreviewWizardClassComponent2() {
    WizardClassComponent(
        classItem = WizardUiState.ClassItem(
            id = 0,
            name = "Barbarian",
            selectableCount = 2,
            savingThrowProficiencyModifiers = listOf(
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = true,
                    editable = false,
                ),
            ),
            selectableSkillProficiencyModifiers = listOf(
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    editable = false,
                ),
            ),
            selected = false,
        ),
        selectClass = {},
        openProficiencyDialog = {},
        modifier = Modifier,
    )
}