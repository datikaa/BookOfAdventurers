package com.datikaa.bookofadventurers.feature.overview.ui

sealed interface OverviewNavigation {
    data object Characters : OverviewNavigation
}
