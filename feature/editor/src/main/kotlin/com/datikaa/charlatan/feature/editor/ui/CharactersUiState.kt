package com.datikaa.charlatan.feature.editor.ui

import androidx.compose.runtime.Immutable
import com.datikaa.charlatan.feature.editor.domain.CmmCharacter

@Immutable
data class CharactersUiState(
    val selectedCharacter: CmmCharacter?,
    val characters: List<CmmCharacter>,
)
