package com.datikaa.charlatan.feature.editor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.feature.editor.domain.AddCharacterUseCase
import com.datikaa.charlatan.feature.editor.domain.FlowCharacterUseCase
import com.datikaa.charlatan.feature.editor.domain.FlowListAllCharactersUseCase
import com.datikaa.charlatan.feature.editor.domain.ModifyAbilityValueOfCharacterUseCase
import com.datikaa.charlatan.feature.editor.domain.ModifyLevelOfCharacterUseCase
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
    private val modifyAbilityValueOfCharacterUseCase: ModifyAbilityValueOfCharacterUseCase,
    private val modifyLevelOfCharacterUseCase: ModifyLevelOfCharacterUseCase,
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

    fun increaseAbility(character: Character, ability: Ability) {
        viewModelScope.launch {
            modifyAbilityValueOfCharacterUseCase(
                character = character,
                ability = ability,
                type = ModifyAbilityValueOfCharacterUseCase.Type.Increase,
            )
        }
    }

    fun decreaseAbility(character: Character, ability: Ability) {
        viewModelScope.launch {
            modifyAbilityValueOfCharacterUseCase(
                character = character,
                ability = ability,
                type = ModifyAbilityValueOfCharacterUseCase.Type.Decrease,
            )
        }
    }

    fun increaseLevel(character: Character) {
        viewModelScope.launch {
            modifyLevelOfCharacterUseCase(
                character = character,
                type = ModifyLevelOfCharacterUseCase.Type.Increase,
            )
        }
    }

    fun decreaseLevel(character: Character) {
        viewModelScope.launch {
            modifyLevelOfCharacterUseCase(
                character = character,
                type = ModifyLevelOfCharacterUseCase.Type.Decrease,
            )
        }
    }
}

private val initialUiState = CharactersUiState(
    selectedCharacter = null,
    characters = listOf(),
)
