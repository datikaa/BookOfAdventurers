package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.runtime.Immutable
import com.datikaa.charlatan.core.domain.Ability

@Immutable
data class OverviewUiState(
    val attributes: List<Ability>
)
