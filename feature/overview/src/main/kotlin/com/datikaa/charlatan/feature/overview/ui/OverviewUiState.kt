package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.runtime.Immutable

@Immutable
data class OverviewUiState(
    val level: Int,
    val name: String,
    val proficiency: Int,
    val abilities: List<UiAbility>,
    val skills: List<UiSkill>,
) {
    data class UiAbility(
        val name: String,
        val shortName: String,
        val baseScore: Int,
        val calculatedScore: Int,
    )

    data class UiSkill(
        val name: String,
        val baseName: String,
        val score: Int,
        val proficiency: Boolean,
    )
}
