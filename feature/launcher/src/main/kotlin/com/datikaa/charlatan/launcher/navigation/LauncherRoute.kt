package com.datikaa.charlatan.launcher.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.datikaa.charlatan.launcher.ui.LauncherRoute

const val launcherRoute = "launcher_route"

fun NavController.navigateToLauncher(navOptions: NavOptions? = null) {
    navigate(launcherRoute, navOptions)
}

fun NavGraphBuilder.launcherScreen(
    openCharacter: (Int) -> Unit,
    openEditor: () -> Unit,
) {
    composable(route = launcherRoute) {
        LauncherRoute(
            openCharacter = openCharacter,
            openEditor = openEditor,
        )
    }
}
