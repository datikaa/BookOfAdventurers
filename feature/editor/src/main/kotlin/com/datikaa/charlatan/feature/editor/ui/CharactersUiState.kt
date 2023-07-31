package com.datikaa.charlatan.feature.editor.ui

import androidx.compose.runtime.Immutable
import com.datikaa.charlatan.core.domain.Character

@Immutable
data class CharactersUiState(
    val selectedCharacter: Character?,
    val characters: List<Character>,
    val modifiers: List<Modifier>,
) {
    data class Modifier(
        val id: Int,
        val name: String,
        val selected: Boolean,
    )
}
