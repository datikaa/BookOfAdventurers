package com.datikaa.charlatan.feature.overview.ui

import androidx.compose.runtime.Immutable
import com.datikaa.charlatan.feature.overview.domain.Attribute

@Immutable
data class OverviewUiState(
    val attributes: List<Attribute>
)
