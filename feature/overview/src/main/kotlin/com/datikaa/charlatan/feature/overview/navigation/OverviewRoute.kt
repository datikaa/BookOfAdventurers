package com.datikaa.charlatan.feature.overview.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.datikaa.charlatan.feature.overview.ui.OverviewRoute

const val characterIdArg = "{characterId}"
const val overviewRoute = "overview_route/$characterIdArg"

fun NavController.navigateToOverview(characterId: Int, navOptions: NavOptions? = null) {
    navigate(overviewRoute.replace(characterIdArg, characterId.toString()), navOptions)
}

fun NavGraphBuilder.overviewScreen() {
    composable(
        route = overviewRoute,
        arguments = listOf(
            navArgument(characterIdArg) { type = NavType.IntType }
        ),
    ) { backStackEntry ->
        OverviewRoute(
            backStackEntry.arguments!!.getInt(characterIdArg),
            modifier = Modifier.fillMaxSize(),
            navigate = { }
        )
    }
}
