package com.datikaa.charlatan.feature.character.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.feature.character.domain.AddCharacterUseCase
import com.datikaa.charlatan.feature.character.domain.CmmCharacter
import com.datikaa.charlatan.feature.character.domain.FlowListAllCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersScreenViewModel(
    private val addCharacterUseCase: AddCharacterUseCase,
    private val flowListAllCharactersUseCase: FlowListAllCharactersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            flowListAllCharactersUseCase().collectLatest { characters ->
                _uiState.update { prev ->
                    prev.copy(
                        characters = characters,
                    )
                }
            }
        }
    }

    fun addCharacter(name: String) {
        viewModelScope.launch {
            addCharacterUseCase(name)
        }
    }

    fun selectCharacter(character: CmmCharacter) {
        _uiState.update { prev ->
            prev.copy(
                selectedCharacter = character,
            )
        }
    }
}

private val initialUiState = CharactersUiState(
    selectedCharacter = null,
    characters = listOf(),
)