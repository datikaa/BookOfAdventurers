package com.datikaa.boa.launcher.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.datikaa.boa.launcher.ui.LauncherRoute

const val launcherRoute = "launcher_route"

fun NavController.navigateToLauncher(navOptions: NavOptions? = null) {
    navigate(launcherRoute, navOptions)
}

fun NavGraphBuilder.launcherScreen(
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
    openModifiers: () -> Unit,
) {
    composable(route = launcherRoute) {
        LauncherRoute(
            openCharacter = openCharacter,
            openEditor = openEditor,
            openModifiers = openModifiers,
        )
    }
}
