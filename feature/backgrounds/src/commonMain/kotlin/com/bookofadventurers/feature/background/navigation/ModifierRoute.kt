package com.bookofadventurers.feature.background.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bookofadventurers.feature.background.ui.BackgroundsRoute

const val backgroundsRoute = "backgrounds_route"

fun NavController.navigateToBackgrounds(navOptions: NavOptions? = null) {
    navigate(backgroundsRoute, navOptions)
}

fun NavGraphBuilder.backgroundsScreen(
    navigateBack: () -> Unit,
) {
    composable(route = backgroundsRoute) {
        BackgroundsRoute(
            navigateBack = navigateBack,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
