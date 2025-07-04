package com.datikaa.bookofadventurers.feature.overview.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.savedstate.read
import com.datikaa.bookofadventurers.feature.overview.ui.OverviewRoute

private const val CHARACTER_ID_ARG = "characterId"
private const val ROUTE_PARAM = "{$CHARACTER_ID_ARG}"
const val overviewRoute = "overview_route/$ROUTE_PARAM"

fun NavController.navigateToOverview(characterId: Int, navOptions: NavOptions? = null) {
    navigate(overviewRoute.replace(ROUTE_PARAM, characterId.toString()), navOptions)
}

fun NavGraphBuilder.overviewScreen(
    navigateBack: () -> Unit,
) {
    composable(
        route = overviewRoute,
        arguments = listOf(
            navArgument(CHARACTER_ID_ARG) { type = NavType.IntType }
        ),
    ) { backStackEntry ->
        OverviewRoute(
            navigateBack = navigateBack,
            backStackEntry.arguments!!.read {getInt(CHARACTER_ID_ARG) },
            modifier = Modifier.fillMaxSize(),
        )
    }
}
