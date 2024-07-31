package com.datikaa.bookofadventurers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.bookofadventurers.feature.background.navigation.backgroundsScreen
import com.bookofadventurers.feature.background.navigation.navigateToBackgrounds
import com.bookofadventurers.feature.wizard.navigation.navigateToWizard
import com.bookofadventurers.feature.wizard.navigation.wizardScreen
import com.datikaa.bookofadventurers.core.analytics.AnalyticsHelper
import com.datikaa.bookofadventurers.core.analytics.LocalAnalyticsHelper
import com.datikaa.bookofadventurers.core.design.theme.BookOfAdventurersTheme
import com.datikaa.bookofadventurers.feature.editor.navigation.editorScreen
import com.datikaa.bookofadventurers.feature.editor.navigation.navigateToEditor
import com.datikaa.bookofadventurers.feature.overview.navigation.navigateToOverview
import com.datikaa.bookofadventurers.feature.overview.navigation.overviewScreen
import com.datikaa.bookofadventurers.launcher.navigation.launcherRoute
import com.datikaa.bookofadventurers.launcher.navigation.launcherScreen
import com.datikaa.bookofadventurers.modifier.navigation.modifierScreen
import com.datikaa.bookofadventurers.modifier.navigation.navigateToModifier
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val analyticsHelper: AnalyticsHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalAnalyticsHelper provides analyticsHelper
            ) {
                BookOfAdventurersTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = launcherRoute) {
                            backgroundsScreen(
                                navigateBack = { navController.popBackStack() },
                            )
                            launcherScreen(
                                versionName = BuildConfig.VERSION_NAME,
                                openBackgrounds = { navController.navigateToBackgrounds() },
                                openEditor = { navController.navigateToEditor() },
                                openCharacter = { id -> navController.navigateToOverview(id) },
                                openModifiers = { navController.navigateToModifier() },
                                openWizard = { navController.navigateToWizard() },
                            )
                            overviewScreen(
                                navigateBack = { navController.popBackStack() },
                            )
                            editorScreen(
                                navigateBack = { navController.popBackStack() },
                                openCharacter = {
                                    navController.navigateToOverview(
                                        characterId = it,
                                        navOptions = navOptions {
                                            launchSingleTop = true
                                        }
                                    )
                                }
                            )
                            modifierScreen(
                                navigateBack = { navController.popBackStack() },
                            )
                            wizardScreen(
                                navigateBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}
