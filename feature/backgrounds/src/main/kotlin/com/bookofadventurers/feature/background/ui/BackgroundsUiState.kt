package com.bookofadventurers.feature.background.ui

import androidx.compose.runtime.Immutable

@Immutable
data class BackgroundsUiState(
    val backgrounds: List<Background>,
    val modifiers: List<Modifier>,
) {

    @Immutable
    data class Background(
        val id: Long,
        val name: String,
        val featureTitle: String,
        val featureDescription: String,
        val suggestedCharacteristics: String,
        val skillProficiencies: List<Modifier>,
    )

    @Immutable
    data class Modifier(
        val id: Int,
        val name: String,
    )
}
