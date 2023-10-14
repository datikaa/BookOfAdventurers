package com.datikaa.bookofadventurers.launcher.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.datikaa.bookofadventurers.launcher.ui.LauncherRoute

const val launcherRoute = "launcher_route"

fun NavController.navigateToLauncher(navOptions: NavOptions? = null) {
    navigate(launcherRoute, navOptions)
}

fun NavGraphBuilder.launcherScreen(
    versionName: String,
    openBackgrounds: () -> Unit,
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
    openModifiers: () -> Unit,
    openWizard: () -> Unit,
) {
    composable(route = launcherRoute) {
        LauncherRoute(
            versionName = versionName,
            openBackgrounds = openBackgrounds,
            openCharacter = openCharacter,
            openEditor = openEditor,
            openModifiers = openModifiers,
            openWizard = openWizard,
        )
    }
}
