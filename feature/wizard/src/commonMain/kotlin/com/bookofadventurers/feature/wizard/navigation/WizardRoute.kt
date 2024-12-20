package com.bookofadventurers.feature.wizard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bookofadventurers.feature.wizard.ui.WizardRoute


const val wizardRoute = "wizard_route"

fun NavController.navigateToWizard(navOptions: NavOptions? = null) {
    this.navigate(wizardRoute, navOptions)
}

fun NavGraphBuilder.wizardScreen(
    navigateBack: () -> Unit,
) {
    composable(route = wizardRoute) {
        WizardRoute(
            navigateBack = navigateBack,
        )
    }
}
