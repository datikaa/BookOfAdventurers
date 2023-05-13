package com.datikaa.charlatan.feature.attributes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.feature.attributes.domain.Attribute
import com.datikaa.charlatan.feature.attributes.domain.Character
import org.koin.androidx.compose.koinViewModel

@Composable
fun ModifiersScreen(
    modifier: Modifier = Modifier,
    viewModel: AttributesViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ModifierView(
        uiState = uiState,
        addAttributeClick = viewModel::addAttribute,
        increaseAttributeClick = viewModel::increaseAttribute,
        decreaseAttributeClick = viewModel::decreaseAttribute,
        selectCharacter = viewModel::characterSelect,
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
private fun ModifierView(
    uiState: AttributesUiState,
    modifier: Modifier = Modifier,
    addAttributeClick: (String) -> Unit,
    decreaseAttributeClick: (Attribute) -> Unit,
    increaseAttributeClick: (Attribute) -> Unit,
    selectCharacter: (Character) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing),
    ) {

        CharacterSelectionCard(
            uiState = uiState,
            selectCharacter = selectCharacter,
            modifier = Modifier.fillMaxWidth()
        )

        AddNewAttributeCard(
            addAttributeClick = addAttributeClick,
            modifier = Modifier.fillMaxWidth(),
        )

        AttributeEditorCard(
            uiState = uiState,
            decreaseAttributeClick = decreaseAttributeClick,
            increaseAttributeClick = increaseAttributeClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterSelectionCard(
    uiState: AttributesUiState,
    selectCharacter: (Character) -> Unit,
    modifier: Modifier = Modifier,
) = CmmTitledCard(
    title = "Character",
    modifier = modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = uiState.selectedCharacter?.name ?: "Select character",
            onValueChange = { /* nothing */ },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            uiState.characters.forEach { character ->
                DropdownMenuItem(
                    text = { Text(text = character.name) },
                    onClick = {
                        selectCharacter(character)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun AddNewAttributeCard(
    addAttributeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) = CmmTitledCard(
    title = "Add new attribute",
    modifier = modifier,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var nameText by rememberSaveable { mutableStateOf("") }
        var nameError by rememberSaveable { mutableStateOf(false) }
        val onAddClick = {
            if (nameText.isNotBlank()) {
                addAttributeClick(nameText)
                nameText = ""
            } else {
                nameError = true
            }
        }
        OutlinedTextField(
            value = nameText,
            onValueChange = {
                nameError = false
                nameText = it
            },
            isError = nameError,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
        )
        Button(onClick = onAddClick, Modifier.fillMaxWidth()) {
            Text("Add")
        }
    }
}

@Composable
private fun AttributeEditorCard(
    uiState: AttributesUiState,
    decreaseAttributeClick: (Attribute) -> Unit,
    increaseAttributeClick: (Attribute) -> Unit,
    modifier: Modifier = Modifier,
) = CmmTitledCard(
    title = "Attributes",
    modifier = modifier,
) {
    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
        uiState.attributeList.forEach { attribute ->
            AttributeEditorLine(
                attribute = attribute,
                decrease = decreaseAttributeClick,
                increase = increaseAttributeClick,
            )
        }
    }
}

@Composable
private fun AttributeEditorLine(
    attribute: Attribute,
    decrease: (Attribute) -> Unit,
    increase: (Attribute) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CharlatanTheme.dimensions.cardSpacing)
    ) {
        OutlinedCard(modifier = modifier) {
            Row {
                IconButton(onClick = { decrease(attribute) }) {
                    Icon(
                        imageVector = Icons.Sharp.KeyboardArrowDown,
                        contentDescription = "decrease"
                    )
                }
                Box(
                    modifier = modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(color = MaterialTheme.colorScheme.outlineVariant)
                )
                IconButton(onClick = { increase(attribute) }) {
                    Icon(
                        imageVector = Icons.Sharp.KeyboardArrowUp,
                        contentDescription = "increase"
                    )
                }
            }
        }
        Text(
            text = "${attribute.name}: ${attribute.value}",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}


@Preview
@Composable
private fun Preview() {
    ModifierView(
        uiState = AttributesUiState(
            characters = listOf(),
            selectedCharacter = null,
            attributeList = listOf(
                Attribute(0, "Str", 6),
                Attribute(0, "Int", 5),
                Attribute(0, "Dex", 4),
            ),
        ),
        modifier = Modifier,
        addAttributeClick = { _ -> /* nothing */ },
        decreaseAttributeClick = { _ -> /* nothing */ },
        increaseAttributeClick = { _ -> /* nothing */ },
        selectCharacter = { _ -> /* nothing */ },
    )
}