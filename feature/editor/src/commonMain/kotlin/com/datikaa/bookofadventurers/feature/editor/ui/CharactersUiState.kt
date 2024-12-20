package com.datikaa.bookofadventurers.feature.editor.ui

import androidx.compose.runtime.Immutable
import com.datikaa.bookofadventurers.core.domain.BoaCharacter

@Immutable
data class CharactersUiState(
    val selectedCharacter: BoaCharacter?,
    val characters: List<BoaCharacter>,
    val classes: List<ClassItem>,
    val modifiers: List<Modifier>,
) {
    data class Modifier(
        val id: Int,
        val name: String,
        val selected: Boolean,
    )

    data class ClassItem(
        val id: Int,
        val name: String,
    )
}
