package com.bookofadventurers.feature.background.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme

@Composable
fun ModifierSelectorDialog(
    modifiers: List<BackgroundsUiState.Modifier>,
    selectedModifiers: List<BackgroundsUiState.Modifier>,
    onDismissRequest: () -> Unit,
    onModifierCheckedChange: (BackgroundsUiState.Modifier, Boolean) -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                ) {
                    Text(text = "Modifiers")
                    IconButton(onClick = { onDismissRequest() }) {
                        Icon(imageVector = Icons.Rounded.Close, contentDescription = "close")
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(BookOfAdventurersTheme.dimensions.cardSpacing),
                    modifier = Modifier.padding(12.dp),
                ) {
                    items(modifiers) { modifierItem ->
                        ModifierItem(
                            name = modifierItem.name,
                            selected = selectedModifiers.contains(modifierItem),
                            onCheckChanged = { checked ->
                                onModifierCheckedChange(modifierItem, checked)
                            }
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ModifierItem(
    name: String,
    selected: Boolean,
    onCheckChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .clickable(
                onClick = { onCheckChanged(!selected) },
                indication = LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(vertical = 2.dp, horizontal = 4.dp),
    ) {
        Text(text = name)
        Checkbox(
            checked = selected,
            interactionSource = interactionSource,
            onCheckedChange = null,
        )
    }
}

@Preview
@Composable
fun ModifierSelectorDialogPreview() {
    ModifierSelectorDialog(
        onDismissRequest = {},
        onModifierCheckedChange = { _, _ -> },
        modifiers = listOf(
            BackgroundsUiState.Modifier(id = 1, name = "Con"),
            BackgroundsUiState.Modifier(id = 2, name = "Str"),
        ),
        selectedModifiers = listOf(
            BackgroundsUiState.Modifier(id = 1, name = "Con"),
        )
    )
}