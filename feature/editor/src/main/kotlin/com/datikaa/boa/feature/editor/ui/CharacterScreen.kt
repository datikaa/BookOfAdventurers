package com.datikaa.boa.feature.editor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.boa.core.design.DevicePreviews
import com.datikaa.boa.core.design.theme.BookOfAdventurersTheme
import com.datikaa.boa.core.domain.Ability
import com.datikaa.boa.core.domain.BoaCharacter
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditorRoute(
    openCharacter: (Int) -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: CharactersScreenViewModel = koinViewModel()
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    CharactersScreen(
        uiState = uiState,
        addCharacter = overviewViewModel::addCharacter,
        selectCharacter = overviewViewModel::selectCharacter,
        openCharacter = { openCharacter(it.id) },
        decreaseAbility = overviewViewModel::decreaseAbility,
        increaseAbility = overviewViewModel::increaseAbility,
        decreaseLevel = overviewViewModel::decreaseLevel,
        increaseLevel = overviewViewModel::increaseLevel,
        addModifier = overviewViewModel::addModifier,
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(BookOfAdventurersTheme.dimensions.screenPadding),
    )
}

@Composable
private fun CharactersScreen(
    uiState: CharactersUiState,
    addCharacter: (String) -> Unit,
    selectCharacter: (BoaCharacter?) -> Unit,
    openCharacter: (BoaCharacter) -> Unit,
    decreaseAbility: (BoaCharacter, Ability) -> Unit,
    increaseAbility: (BoaCharacter, Ability) -> Unit,
    decreaseLevel: (BoaCharacter) -> Unit,
    increaseLevel: (BoaCharacter) -> Unit,
    addModifier: (BoaCharacter, Int) -> Unit,
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
            decreaseLevel = decreaseLevel,
            increaseLevel = increaseLevel,
            addModifier = addModifier,
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
                BoaCharacter(
                    id = 3144,
                    name = "Dax",
                    level = 8884,
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
            modifiers = listOf(),
        ),
        addCharacter = { /* nothing */ },
        selectCharacter = { /* nothing */ },
        openCharacter = { /* nothing */ },
        decreaseAbility = { _, _ -> /* nothing */ },
        increaseAbility = { _, _ -> /* nothing */ },
        decreaseLevel = { _ -> /* nothing */ },
        increaseLevel = { _ -> /* nothing */ },
        addModifier = { _, _ -> /* nothing */ }
    )
}
