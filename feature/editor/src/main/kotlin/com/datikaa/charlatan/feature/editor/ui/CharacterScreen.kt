package com.datikaa.charlatan.feature.editor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditorRoute(
    openCharacter: (Int) -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: CharactersScreenViewModel = koinViewModel()
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    CharactersScreen(
        uiState = uiState,
        addCharacter = overviewViewModel::addCharacter,
        selectCharacter = overviewViewModel::selectCharacter,
        openCharacter = { openCharacter(it.id) },
        decreaseAbility = overviewViewModel::decreaseAttribute,
        increaseAbility = overviewViewModel::increaseAttribute,
        modifier = modifier.padding(CharlatanTheme.dimensions.screenPadding),
    )
}

@Composable
private fun CharactersScreen(
    uiState: CharactersUiState,
    addCharacter: (String) -> Unit,
    selectCharacter: (Character?) -> Unit,
    openCharacter: (Character) -> Unit,
    decreaseAbility: (Character, Ability) -> Unit,
    increaseAbility: (Character, Ability) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (uiState.selectedCharacter == null) {
        CharacterList(
            uiState = uiState,
            addCharacter = addCharacter,
            selectCharacter = selectCharacter,
            modifier = modifier,
        )
    } else {
        CharacterEditor(
            uiState = uiState,
            openCharacterSheet = openCharacter,
            back = { selectCharacter(null) },
            decreaseAbility = decreaseAbility,
            increaseAbility = increaseAbility,
            modifier = modifier,
        )
    }
}

@DevicePreviews
@Composable
private fun Preview() {
    CharactersScreen(
        uiState = CharactersUiState(
            selectedCharacter = null,
            characters = listOf(
                Character(
                    id = 3144,
                    name = "Dax",
                    level = 8884,
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            )
        ),
        addCharacter = { /* nothing */ },
        selectCharacter = { /* nothing */ },
        openCharacter = { /* nothing */ },
        decreaseAbility = { _, _ -> /* nothing */ },
        increaseAbility = { _, _ -> /* nothing */ },
    )
}

// TODO: this should be in a ":core:ui" module or something
private const val DEVICES = "devices"

@Preview(
    name = "Phone",
    group = DEVICES,
    device = Devices.PIXEL_2,
)
@Preview(
    name = "Tablet",
    group = DEVICES,
    device = Devices.PIXEL_C,
)
annotation class DevicePreviews