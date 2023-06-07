package com.datikaa.charlatan.feature.editor.ui

import androidx.compose.runtime.Immutable
import com.datikaa.charlatan.core.domain.Character

@Immutable
data class CharactersUiState(
    val selectedCharacter: Character?,
    val characters: List<Character>,
)
