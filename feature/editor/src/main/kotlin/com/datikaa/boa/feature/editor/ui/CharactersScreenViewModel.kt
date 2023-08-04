package com.datikaa.boa.feature.editor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.boa.core.domain.Ability
import com.datikaa.boa.core.domain.BoaCharacter
import com.datikaa.boa.feature.editor.domain.AddCharacterUseCase
import com.datikaa.boa.feature.editor.domain.AddOrRemoveModifierToCharacterUseCase
import com.datikaa.boa.feature.editor.domain.FlowCharacterUseCase
import com.datikaa.boa.feature.editor.domain.FlowListAllCharactersUseCase
import com.datikaa.boa.feature.editor.domain.FlowSelectedModifiersForCharacterUseCase
import com.datikaa.boa.feature.editor.domain.ModifyAbilityValueOfCharacterUseCase
import com.datikaa.boa.feature.editor.domain.ModifyLevelOfCharacterUseCase
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
    private val addOrRemoveModifierToCharacterUseCase: AddOrRemoveModifierToCharacterUseCase,
    private val flowListAllCharactersUseCase: FlowListAllCharactersUseCase,
    private val flowCharacterUseCase: FlowCharacterUseCase,
    private val flowSelectedModifiersForCharacterUseCase: FlowSelectedModifiersForCharacterUseCase,
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
        viewModelScope.launch {
            selectedCharacterId.flatMapLatest {
                if (it != null) flowSelectedModifiersForCharacterUseCase(it) else flowOf(null)
            }.collectLatest { modifiers ->
                _uiState.update { prev ->
                    prev.copy(
                        modifiers = modifiers.orEmpty().map { (modifier, selected) ->
                            CharactersUiState.Modifier(modifier.id, modifier.name, selected)
                        },
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

    fun selectCharacter(character: BoaCharacter?) {
        selectedCharacterId.update { _ ->
            character?.id
        }
    }

    fun increaseAbility(character: BoaCharacter, ability: Ability) {
        viewModelScope.launch {
            modifyAbilityValueOfCharacterUseCase(
                character = character,
                ability = ability,
                type = ModifyAbilityValueOfCharacterUseCase.Type.Increase,
            )
        }
    }

    fun decreaseAbility(character: BoaCharacter, ability: Ability) {
        viewModelScope.launch {
            modifyAbilityValueOfCharacterUseCase(
                character = character,
                ability = ability,
                type = ModifyAbilityValueOfCharacterUseCase.Type.Decrease,
            )
        }
    }

    fun increaseLevel(character: BoaCharacter) {
        viewModelScope.launch {
            modifyLevelOfCharacterUseCase(
                character = character,
                type = ModifyLevelOfCharacterUseCase.Type.Increase,
            )
        }
    }

    fun decreaseLevel(character: BoaCharacter) {
        viewModelScope.launch {
            modifyLevelOfCharacterUseCase(
                character = character,
                type = ModifyLevelOfCharacterUseCase.Type.Decrease,
            )
        }
    }

    fun addModifier(character: BoaCharacter, modifierId: Int) {
        viewModelScope.launch {
            addOrRemoveModifierToCharacterUseCase(
                characterId = character.id,
                modifierId = modifierId,
            )
        }
    }
}

private val initialUiState = CharactersUiState(
    selectedCharacter = null,
    characters = listOf(),
    modifiers = listOf(),
)
