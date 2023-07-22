package com.datikaa.charlatan.launcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.launcher.FlowCharacterNamesUseCase
import com.datikaa.charlatan.launcher.LauncherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class LauncherViewModel(
    flowCharacterNamesUseCase: FlowCharacterNamesUseCase,
) : ViewModel(

) {
    private val _uiState = MutableStateFlow(initialState())
    val uiState: StateFlow<LauncherUiState> = _uiState

    init {
        flowCharacterNamesUseCase().onEach { characters ->
            _uiState.update { oldState ->
                oldState.copy(
                    characters = characters.map {
                        LauncherUiState.CharacterListItem(
                            id = it.id, name = "${it.name} (Lvl ${it.level})"
                        )
                    }
                )
            }
        }.launchIn(viewModelScope)
    }
}

private fun initialState() = LauncherUiState(
    characters = listOf(),
    modifiers = listOf(),
)
