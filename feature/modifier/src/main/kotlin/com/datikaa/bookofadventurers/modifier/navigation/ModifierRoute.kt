package com.datikaa.bookofadventurers.modifier.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.datikaa.bookofadventurers.modifier.ui.ModifierRoute

const val modifierRoute = "modifier_route"

fun NavController.navigateToModifier(navOptions: NavOptions? = null) {
    navigate(modifierRoute, navOptions)
}

fun NavGraphBuilder.modifierScreen(
    navigateBack: () -> Unit,
) {
    composable(route = modifierRoute) {
        ModifierRoute(
            navigateBack = navigateBack,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
