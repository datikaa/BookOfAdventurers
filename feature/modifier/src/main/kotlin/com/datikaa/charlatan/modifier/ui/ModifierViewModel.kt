package com.datikaa.charlatan.modifier.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.modifier.domain.CreateNewModifierUseCase
import com.datikaa.charlatan.modifier.domain.FlowAllModifiersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ModifierViewModel(
    flowAllModifiersUseCase: FlowAllModifiersUseCase,
    private val createNewModifierUseCase: CreateNewModifierUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState())
    val uiState: StateFlow<ModifierUiState> = _uiState

    init {
        flowAllModifiersUseCase().onEach { modifiers ->
            _uiState.update { oldState ->
                oldState.copy(
                    modifiers = modifiers,
                )
            }
        }.launchIn(viewModelScope)
    }

    fun createModifier(newModifier: AddNewModifier.Modifier) = viewModelScope.launch {
        createNewModifierUseCase(newModifier)
    }
}

private fun initialUiState() = ModifierUiState(
    modifiers = listOf(),
)
