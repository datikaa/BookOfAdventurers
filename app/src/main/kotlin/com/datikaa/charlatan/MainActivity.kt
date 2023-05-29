package com.datikaa.charlatan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.feature.editor.navigation.editorRoute
import com.datikaa.charlatan.feature.editor.navigation.editorScreen
import com.datikaa.charlatan.feature.overview.ui.OverviewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CharlatanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = editorRoute) {
                        composable(
                            route = "overview/{characterId}",
                            arguments = listOf(
                                navArgument("characterId") { type = NavType.IntType }
                            ),
                        ) { backStackEntry ->
                            OverviewScreen(
                                backStackEntry.arguments?.getInt("characterId")!!, // TODO
                                modifier = Modifier.fillMaxSize(),
                                navigate = { navController.navigate(it) })
                        }
                        editorScreen()
                    }
                }
            }
        }
    }
}
