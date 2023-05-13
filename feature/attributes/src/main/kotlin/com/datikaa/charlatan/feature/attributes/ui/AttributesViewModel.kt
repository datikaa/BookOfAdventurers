package com.datikaa.charlatan.feature.attributes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.feature.attributes.domain.AddAttributeUseCase
import com.datikaa.charlatan.feature.attributes.domain.Attribute
import com.datikaa.charlatan.feature.attributes.domain.Character
import com.datikaa.charlatan.feature.attributes.domain.DecreaseValueOfAttributeUseCase
import com.datikaa.charlatan.feature.attributes.domain.FlowListOfCharacterAttributesUseCase
import com.datikaa.charlatan.feature.attributes.domain.FlowListOfCharactersUseCase
import com.datikaa.charlatan.feature.attributes.domain.IncreaseValueOfAttributeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AttributesViewModel(
    flowListOfCharacterAttributesUseCase: FlowListOfCharacterAttributesUseCase,
    flowListOfCharactersUseCase: FlowListOfCharactersUseCase,
    private val addAttributeUseCase: AddAttributeUseCase,
    private val decreaseValueOfAttributeUseCase: DecreaseValueOfAttributeUseCase,
    private val increaseValueOfAttributeUseCase: IncreaseValueOfAttributeUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    init {
        flowListOfCharactersUseCase().onEach { characters ->
            _uiState.update { prev ->
                prev.copy(characters = characters)
            }
        }.launchIn(viewModelScope)
        uiState
            .map {
                it.selectedCharacter
            }.distinctUntilChanged()
            .filterNotNull()
            .flatMapLatest {
                flowListOfCharacterAttributesUseCase(it)
            }.onEach { attributes ->
                _uiState.update { prev ->
                    prev.copy(attributeList = attributes)
                }
            }.launchIn(viewModelScope)
    }

    fun addAttribute(text: String) {
        viewModelScope.launch {
            addAttributeUseCase(text, 1)
        }
    }

    fun increaseAttribute(attribute: Attribute) {
        viewModelScope.launch {
            val selectedCharacter = uiState.value.selectedCharacter ?: return@launch
            increaseValueOfAttributeUseCase(attribute, selectedCharacter)
        }
    }

    fun decreaseAttribute(attribute: Attribute) {
        viewModelScope.launch {
            val selectedCharacter = uiState.value.selectedCharacter ?: return@launch
            decreaseValueOfAttributeUseCase(attribute, selectedCharacter)
        }
    }

    fun characterSelect(character: Character) {
        _uiState.update { prev ->
            prev.copy(selectedCharacter = character)
        }
    }
}

private val initialUiState = AttributesUiState(
    attributeList = listOf(),
    characters = listOf(),
    selectedCharacter = null
)
