package com.datikaa.bookofadventurers.feature.overview.ui

import androidx.compose.runtime.Immutable

@Immutable
data class OverviewUiState(
    val level: Int,
    val name: String,
    val className: String,
    val proficiency: Int,
    val abilities: List<UiAbility>,
    val skills: List<UiSkill>,
    val savingThrows: List<UiSavingThrow>,
) {

    @Immutable
    data class UiAbility(
        val name: String,
        val shortName: String,
        val baseScore: Int,
        val calculatedScore: Int,
    )

    @Immutable
    data class UiSkill(
        val name: String,
        val baseName: String,
        val score: Int,
        val proficiency: Boolean,
    )

    @Immutable
    data class UiSavingThrow(
        val name: String,
        val baseName: String,
        val score: Int,
        val proficiency: Boolean,
    )
}
