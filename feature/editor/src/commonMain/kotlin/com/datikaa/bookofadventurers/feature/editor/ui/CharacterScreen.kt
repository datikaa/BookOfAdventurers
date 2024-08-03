package com.datikaa.bookofadventurers.feature.editor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EditorRoute(
    navigateBack: () -> Unit,
    openCharacter: (Int) -> Unit,
    modifier: Modifier = Modifier,
    overviewViewModel: CharactersScreenViewModel = koinViewModel()
) {
    val uiState by overviewViewModel.uiState.collectAsStateWithLifecycle()

    CharactersScreen(
        uiState = uiState,
        navigateBack = navigateBack,
        selectCharacter = overviewViewModel::selectCharacter,
        openCharacter = { openCharacter(it.id) },
        decreaseAbility = overviewViewModel::decreaseAbility,
        increaseAbility = overviewViewModel::increaseAbility,
        decreaseLevel = overviewViewModel::decreaseLevel,
        increaseLevel = overviewViewModel::increaseLevel,
        addModifier = overviewViewModel::addModifier,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharactersScreen(
    uiState: CharactersUiState,
    navigateBack: () -> Unit,
    selectCharacter: (BoaCharacter?) -> Unit,
    openCharacter: (BoaCharacter) -> Unit,
    decreaseAbility: (BoaCharacter, Ability) -> Unit,
    increaseAbility: (BoaCharacter, Ability) -> Unit,
    decreaseLevel: (BoaCharacter) -> Unit,
    increaseLevel: (BoaCharacter) -> Unit,
    addModifier: (BoaCharacter, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = uiState.selectedCharacter?.name ?: "Characters") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "backIcon")
                    }
                },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        if (uiState.selectedCharacter == null) {
            CharacterList(
                uiState = uiState,
                selectCharacter = selectCharacter,
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(state = scrollState)
                    .padding(BookOfAdventurersTheme.dimensions.screenPadding),
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
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(state = scrollState)
                    .padding(BookOfAdventurersTheme.dimensions.screenPadding),
            )
        }
    }
}

@Preview
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
                    characterBackground = Background(1, "Acolyte", "", "", "", emptyList()),
                    characterClass = CharacterClass(1, "Wizard", emptyList(), emptyList()),
                    abilityList = listOf(),
                    modifiers = listOf()
                )
            ),
            classes = emptyList(),
            modifiers = listOf(),
        ),
        navigateBack = {},
        selectCharacter = {},
        openCharacter = {},
        decreaseAbility = { _, _ -> },
        increaseAbility = { _, _ -> },
        decreaseLevel = { _ -> },
        increaseLevel = { _ -> },
        addModifier = { _, _ -> }
    )
}
