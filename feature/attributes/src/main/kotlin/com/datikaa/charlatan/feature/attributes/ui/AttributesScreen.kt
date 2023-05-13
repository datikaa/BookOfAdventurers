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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.component.CmmTitledCard
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.feature.attributes.domain.Attribute
import org.koin.androidx.compose.koinViewModel

@Composable
fun ModifiersScreen(
    modifier: Modifier = Modifier,
    viewModel: AttributesViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ModifierView(
        attributesUiState = uiState,
        addAttributeClick = viewModel::addAttribute,
        increaseAttributeClick = viewModel::increaseAttribute,
        decreaseAttributeClick = viewModel::decreaseAttribute,
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
private fun ModifierView(
    attributesUiState: AttributesUiState,
    modifier: Modifier = Modifier,
    addAttributeClick: (String, Int) -> Unit,
    decreaseAttributeClick: (Attribute) -> Unit,
    increaseAttributeClick: (Attribute) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        CmmTitledCard(
            title = "Add new attribute",
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                var nameText by rememberSaveable { mutableStateOf("") }
                var valueText by rememberSaveable { mutableStateOf("10") }
                var nameError by rememberSaveable { mutableStateOf(false) }
                var valueError by rememberSaveable { mutableStateOf(false) }
                val onAddClick = {
                    nameError = nameText.isBlank()
                    valueError = valueText.isBlank() || !valueText.isDigitsOnly()
                    if (!nameError && !valueError) {
                        addAttributeClick(nameText, valueText.toInt())
                        nameText = ""
                        valueText = "10"
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
                OutlinedTextField(
                    value = valueText,
                    onValueChange = {
                        valueError = false
                        if (it.isDigitsOnly()) {
                            valueText = it
                        }
                    },
                    isError = valueError,
                    label = { Text("Value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                )
                Button(onClick = onAddClick, Modifier.fillMaxWidth()) {
                    Text("Add")
                }
            }
        }

        CmmTitledCard(title = "Attributes") {
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                attributesUiState.attributeList.forEach { attribute ->
                    AttributeEditor(
                        attribute = attribute,
                        decrease = decreaseAttributeClick,
                        increase = increaseAttributeClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun AttributeEditor(
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
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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
        attributesUiState = AttributesUiState(
            attributeList = listOf(
                Attribute(0, "Str", 6),
                Attribute(0, "Int", 5),
                Attribute(0, "Dex", 4),
            ),
        ),
        modifier = Modifier,
        addAttributeClick = { _, _ -> /* nothing */ },
        decreaseAttributeClick = { _ -> /* nothing */ },
        increaseAttributeClick = { _ -> /* nothing */ },
    )
}