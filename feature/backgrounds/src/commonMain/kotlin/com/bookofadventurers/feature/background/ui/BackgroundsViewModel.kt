package com.bookofadventurers.feature.background.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookofadventurers.feature.background.domain.AddNewBackgroundUseCase
import com.bookofadventurers.feature.background.domain.FlowAllBackgroundsUseCase
import com.bookofadventurers.feature.background.domain.FlowAllModifiersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BackgroundsViewModel(
    flowAllBackgroundsUseCase: FlowAllBackgroundsUseCase,
    flowAllModifiersUseCase: FlowAllModifiersUseCase,
    private val addNewBackgroundUseCase: AddNewBackgroundUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState())
    val uiState: StateFlow<BackgroundsUiState> = _uiState

    init {
        flowAllBackgroundsUseCase().onEach {
            _uiState.update { oldState ->
                oldState.copy(
                    backgrounds = it.map { domain ->
                        BackgroundsUiState.Background(
                            id = domain.id,
                            name = domain.name,
                            featureTitle = domain.featureTitle,
                            featureDescription = domain.featureDescription,
                            suggestedCharacteristics = domain.suggestedCharacteristics,
                            skillProficiencies = domain.skillProficiencies.map { domainModifier ->
                                BackgroundsUiState.Modifier(
                                    id = domainModifier.id,
                                    name = domainModifier.name,
                                )
                            }
                        )
                    }
                )
            }
        }.launchIn(viewModelScope)
        flowAllModifiersUseCase().onEach { domainModifiers ->
            _uiState.update { oldState ->
                oldState.copy(
                    modifiers = domainModifiers.map { domainModifier ->
                        BackgroundsUiState.Modifier(
                            id = domainModifier.id,
                            name = domainModifier.name,
                        )
                    }
                )
            }
        }.launchIn(viewModelScope)
    }

    fun addNewBackground(
        name: String,
        featureTitle: String,
        featureDescription: String,
        suggestedCharacteristics: String,
        modifierIds: List<Long>,
    ) {
        viewModelScope.launch {
            addNewBackgroundUseCase(
                name,
                featureTitle,
                featureDescription,
                suggestedCharacteristics,
                modifierIds
            )
        }
    }
}

private fun initialUiState() = BackgroundsUiState(
    backgrounds = emptyList(),
    modifiers = emptyList(),
)
