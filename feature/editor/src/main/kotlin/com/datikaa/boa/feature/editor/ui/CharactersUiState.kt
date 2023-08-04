package com.datikaa.boa.feature.editor.ui

import androidx.compose.runtime.Immutable
import com.datikaa.boa.core.domain.BoaCharacter

@Immutable
data class CharactersUiState(
    val selectedCharacter: BoaCharacter?,
    val characters: List<BoaCharacter>,
    val modifiers: List<Modifier>,
) {
    data class Modifier(
        val id: Int,
        val name: String,
        val selected: Boolean,
    )
}
