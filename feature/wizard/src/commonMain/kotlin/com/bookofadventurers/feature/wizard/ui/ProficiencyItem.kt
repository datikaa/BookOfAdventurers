package com.bookofadventurers.feature.wizard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProficiencyItem(
    proficiencyModifier: WizardUiState.Modifier,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Box {
            Icon(
                imageVector = if (proficiencyModifier.selected) Icons.Rounded.Check else Icons.Rounded.Close,
                contentDescription = "check",
                tint = if (proficiencyModifier.selected) Color.Green else Color.Red,
                modifier = Modifier
                    .blur(3.dp)
                    .alpha(0.7f),
            )
            Icon(
                imageVector = if (proficiencyModifier.selected) Icons.Rounded.Check else Icons.Rounded.Close,
                contentDescription = "check",
            )
        }
        Text(text = proficiencyModifier.name)
    }
}

@Preview
@Composable
fun ProficiencyItemPreview() {
    ProficiencyItem(
        WizardUiState.Modifier(
            id = 0,
            name = "dexterity",
            selected = true,
            editable = false,
        )
    )
}

@Preview
@Composable
fun ProficiencyItemFalsePreview() {
    ProficiencyItem(
        WizardUiState.Modifier(
            id = 0,
            name = "dexterity",
            selected = false,
            editable = false,
        )
    )
}