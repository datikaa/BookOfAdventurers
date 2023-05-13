package com.datikaa.charlatan.feature.character.ui

import androidx.compose.runtime.Immutable
import com.datikaa.charlatan.feature.character.domain.CmmCharacter

@Immutable
data class CharactersUiState(
    val selectedCharacter: CmmCharacter?,
    val characters: List<CmmCharacter>,
)
