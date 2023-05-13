package com.datikaa.charlatan.feature.overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.feature.overview.domain.ClearEverythingUseCase
import com.datikaa.charlatan.feature.overview.domain.FlowListOfAttributesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val clearEverythingUseCase: ClearEverythingUseCase,
    private val flowListOfAttributesUseCase: FlowListOfAttributesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initUiState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            flowListOfAttributesUseCase().collectLatest { attrs ->
                _uiState.update { uiState ->
                    uiState.copy(attributes = attrs)
                }
            }
        }
    }

    fun clearDb() {
        viewModelScope.launch {
            clearEverythingUseCase()
        }
    }
}

private val initUiState = OverviewUiState(
    attributes = emptyList()
)
