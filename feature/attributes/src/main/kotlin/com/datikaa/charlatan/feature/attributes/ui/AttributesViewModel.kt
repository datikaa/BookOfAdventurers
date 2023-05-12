package com.datikaa.charlatan.feature.attributes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.core.database.CharAttribute
import com.datikaa.charlatan.core.database.CmmDatabase
import com.datikaa.charlatan.feature.attributes.domain.Attribute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AttributesViewModel(
    private val db: CmmDatabase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AttributesUiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            db.characterDao().getAttributes().collectLatest { attributes ->
                _uiState.update { prev ->
                    prev.copy(
                        attributeList = attributes.map { it.toDomain() }
                    )
                }
            }
        }
    }

    fun addAttribute(text: String, value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            db.characterDao().insertAttribute(
                CharAttribute(0, text, value)
            )
        }
    }
}

private fun CharAttribute.toDomain() = Attribute(
    name = name, value = value,
)