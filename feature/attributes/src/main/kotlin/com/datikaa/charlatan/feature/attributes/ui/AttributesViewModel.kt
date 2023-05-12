package com.datikaa.charlatan.feature.attributes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.feature.attributes.domain.AddAttributeUseCase
import com.datikaa.charlatan.feature.attributes.domain.FlowListOfAttributesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AttributesViewModel(
    private val addAttributeUseCase: AddAttributeUseCase,
    private val flowListOfAttributesUseCase: FlowListOfAttributesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AttributesUiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            flowListOfAttributesUseCase().collectLatest { newList ->
                _uiState.update { prev ->
                    prev.copy(attributeList = newList)
                }
            }
        }
    }

    fun addAttribute(text: String, value: Int) {
        viewModelScope.launch {
            addAttributeUseCase(text, value)
        }
    }
}
