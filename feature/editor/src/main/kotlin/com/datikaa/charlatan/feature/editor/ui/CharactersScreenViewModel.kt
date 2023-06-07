package com.datikaa.charlatan.feature.editor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.feature.editor.domain.AddCharacterUseCase
import com.datikaa.charlatan.feature.editor.domain.FlowCharacterUseCase
import com.datikaa.charlatan.feature.editor.domain.FlowListAllCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersScreenViewModel(
    private val addCharacterUseCase: AddCharacterUseCase,
    private val flowListAllCharactersUseCase: FlowListAllCharactersUseCase,
    private val flowCharacterUseCase: FlowCharacterUseCase,
) : ViewModel() {

    private val selectedCharacterId: MutableStateFlow<Int?> = MutableStateFlow(null)

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
        viewModelScope.launch {
            selectedCharacterId.flatMapLatest {
                if (it != null) flowCharacterUseCase(it) else flowOf(null)
            }.collectLatest { selectedCharacter ->
                _uiState.update { prev ->
                    prev.copy(
                        selectedCharacter = selectedCharacter,
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

    fun selectCharacter(character: Character?) {
        selectedCharacterId.update { _ ->
            character?.id
        }
    }
}

private val initialUiState = CharactersUiState(
    selectedCharacter = null,
    characters = listOf(),
)