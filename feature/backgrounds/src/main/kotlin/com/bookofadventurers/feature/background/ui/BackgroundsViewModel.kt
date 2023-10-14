package com.bookofadventurers.feature.background.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookofadventurers.feature.background.domain.FlowAllBackgroundsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class BackgroundsViewModel(
    flowAllBackgroundsUseCase: FlowAllBackgroundsUseCase,
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
    }
}

private fun initialUiState() = BackgroundsUiState(
    backgrounds = emptyList(),
)
