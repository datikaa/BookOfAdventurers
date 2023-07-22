package com.datikaa.charlatan.launcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.launcher.domain.ClearEverythingUseCase
import com.datikaa.charlatan.launcher.domain.FlowAllModifiersUseCase
import com.datikaa.charlatan.launcher.domain.FlowCharacterNamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LauncherViewModel(
    flowCharacterNamesUseCase: FlowCharacterNamesUseCase,
    flowAllModifiersUseCase: FlowAllModifiersUseCase,
    private val clearEverythingUseCase: ClearEverythingUseCase,
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
        flowAllModifiersUseCase().onEach { modifiers ->
            _uiState.update { oldState ->
                oldState.copy(
                    modifiers = modifiers.map { modifier ->
                        LauncherUiState.ModifiersListItem(
                            id = modifier.id,
                            name = modifier.name,
                        )
                    }
                )
            }
        }.launchIn(viewModelScope)
    }

    fun clearDb() {
        viewModelScope.launch {
            clearEverythingUseCase()
        }
    }
}

private fun initialState() = LauncherUiState(
    characters = listOf(),
    modifiers = listOf(),
)
