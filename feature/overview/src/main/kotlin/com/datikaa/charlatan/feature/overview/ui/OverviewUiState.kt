package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.runtime.Immutable

@Immutable
data class OverviewUiState(
    val name: String,
    val abilities: List<UiAbility>
) {
    data class UiAbility(
        val name: String,
        val shortName: String,
        val baseScore: Int,
        val calculatedScore: Int,
    )
}
