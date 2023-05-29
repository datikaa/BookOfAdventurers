package com.datikaa.charlatan.feature.overview.navigation

import android.os.Build
import android.os.Parcelable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.datikaa.charlatan.feature.overview.ui.OverviewRoute
import kotlinx.parcelize.Parcelize

const val overviewRoute = "overview_route/{characterId}"
const val overviewRouteArgs = "overviewRouteArgs"

@Parcelize
data class OverviewRouteArgs(
    val characterId: Int,
) : Parcelable

fun NavController.navigateToOverview(args: OverviewRouteArgs, navOptions: NavOptions? = null) {
    navigate(overviewRoute.replace("{characterId}", args.characterId.toString()), navOptions)
}

fun NavGraphBuilder.overviewScreen() {
    composable(
        route = overviewRoute,
        arguments = listOf(
            navArgument(overviewRouteArgs) {
                type = NavType.ParcelableType(OverviewRouteArgs::class.java)
            }
        ),
    ) { backStackEntry ->
        OverviewRoute(
            backStackEntry.compatGetParcelable(overviewRouteArgs),
            modifier = Modifier.fillMaxSize(),
            navigate = { }
        )
    }
}

// TODO: move this to some common place
private inline fun <reified T : Parcelable> NavBackStackEntry.compatGetParcelable(key: String): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments!!.getParcelable(key, T::class.java)!!
    } else {
        arguments!!.getParcelable<T>(key)!!
    }
}