package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme

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
            Divider(modifier = Modifier.fillMaxWidth())
            Text(
                text = "Proficiencies",
                style = MaterialTheme.typography.headlineSmall,
            )
            Divider(modifier = Modifier.fillMaxWidth())
            Column(verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing)) {
                classItem.savingThrowProficiencyModifiers.forEach {
                    ProficiencyItem(it)
                }
            }
            Divider(modifier = Modifier.fillMaxWidth())
            Column(
                verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                modifier = Modifier.fillMaxWidth()
            ) {
                val selectedSkillProficiencyModifiers =
                    classItem.selectableSkillProficiencyModifiers.filter { it.selected }
                selectedSkillProficiencyModifiers.forEach {
                    ProficiencyItem(it)
                }
                TextButton(
                    onClick = { openProficiencyDialog(classItem.id) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    val remainingSelectionCount =
                        classItem.selectableCount - selectedSkillProficiencyModifiers.size
                    Text(text = if (remainingSelectionCount != 0) "Select $remainingSelectionCount skill proficiency" else "Edit selection")
                }
            }
        }
    }
}

@Composable
fun ProficiencyItem(
    proficiencyModifier: WizardUiState.Modifier,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            imageVector = if (proficiencyModifier.selected) Icons.Rounded.Check else Icons.Rounded.Close,
            contentDescription = "check"
        )
        Text(text = proficiencyModifier.name)
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
                    selectable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = true,
                    selectable = false,
                ),
            ),
            selectableSkillProficiencyModifiers = listOf(
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = false,
                    selectable = false,
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
                    selectable = false,
                ),
                WizardUiState.Modifier(
                    id = 0,
                    name = "constitution",
                    selected = true,
                    selectable = false,
                ),
            ),
            selectableSkillProficiencyModifiers = listOf(
                WizardUiState.Modifier(
                    id = 0,
                    name = "dexterity",
                    selected = true,
                    selectable = false,
                ),
            ),
            selected = true,
        ),
        selectClass = {},
        openProficiencyDialog = {},
        modifier = Modifier,
    )
}