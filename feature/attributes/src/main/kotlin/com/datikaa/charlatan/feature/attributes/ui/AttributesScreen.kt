package com.datikaa.charlatan.feature.attributes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
private fun ModifierView(
    attributesUiState: AttributesUiState,
    modifier: Modifier = Modifier,
    addAttributeClick: (String, Int) -> Unit,
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
                var nameText by remember { mutableStateOf("") }
                var valueText by remember { mutableStateOf("10") }
                var nameError by remember { mutableStateOf(false) }
                var valueError by remember { mutableStateOf(false) }
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
                    label = { Text("Value") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = valueError,
                    modifier = Modifier.fillMaxWidth(),
                )
                Button(onClick = onAddClick, Modifier.fillMaxWidth()) {
                    Text("Add")
                }
            }
        }

        CmmTitledCard(title = "Attributes") {
            Column {
                attributesUiState.attributeList.forEach { attribute ->
                    Text("${attribute.name}: ${attribute.value}")
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ModifierView(
        attributesUiState = AttributesUiState(
            attributeList = listOf(
                Attribute("Str", 6),
                Attribute("Int", 5),
                Attribute("Dex", 4),
            ),
        ),
        modifier = Modifier,
        addAttributeClick = { _, _ -> /* nothing */ }
    )
}