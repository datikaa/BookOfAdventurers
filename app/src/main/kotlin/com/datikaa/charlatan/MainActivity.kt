package com.datikaa.charlatan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import com.datikaa.charlatan.feature.editor.navigation.editorScreen
import com.datikaa.charlatan.feature.editor.navigation.navigateToEditor
import com.datikaa.charlatan.feature.overview.navigation.navigateToOverview
import com.datikaa.charlatan.feature.overview.navigation.overviewScreen
import com.datikaa.charlatan.launcher.navigation.launcherRoute
import com.datikaa.charlatan.launcher.navigation.launcherScreen
import com.datikaa.charlatan.modifier.navigation.modifierScreen
import com.datikaa.charlatan.modifier.navigation.navigateToModifier

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
                    NavHost(navController = navController, startDestination = launcherRoute) {
                        launcherScreen(
                            openEditor = { navController.navigateToEditor() },
                            openCharacter = { id -> navController.navigateToOverview(id) },
                            openModifiers = { navController.navigateToModifier() },
                        )
                        overviewScreen()
                        editorScreen {
                            navController.navigateToOverview(
                                characterId = it,
                                navOptions = navOptions {
                                    launchSingleTop = true
                                }
                            )
                        }
                        modifierScreen()
                    }
                }
            }
        }
    }
}
