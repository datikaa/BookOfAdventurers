package com.datikaa.charlatan.feature.overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datikaa.charlatan.core.database.CharAttribute
import com.datikaa.charlatan.core.database.Character
import com.datikaa.charlatan.core.database.CmmDatabase
import com.datikaa.charlatan.feature.overview.domain.Attribute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val db: CmmDatabase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initUiState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            db.characterDao().getAttributes().collect { attrs ->
                _uiState.update { uiState ->
                    uiState.copy(attributes = attrs.map { it.toDomain() })
                }
            }
        }
    }

    fun addChar(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.characterDao().insert(
                Character(1, text)
            )
        }
    }

    fun clearDb() {
        viewModelScope.launch(Dispatchers.IO) {
            db.characterDao().deleteAttributes()
            db.characterDao().deleteCharacter()
        }
    }
}

private fun CharAttribute.toDomain() = Attribute(
    name = name, value = value,
)

private val initUiState = OverviewUiState(
    attributes = emptyList()
)
