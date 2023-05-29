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
import com.datikaa.charlatan.feature.editor.navigation.editorRoute
import com.datikaa.charlatan.feature.editor.navigation.editorScreen
import com.datikaa.charlatan.feature.overview.navigation.navigateToOverview
import com.datikaa.charlatan.feature.overview.navigation.overviewScreen

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
                        overviewScreen()
                        editorScreen {
                            navController.navigateToOverview(
                                characterId = it,
                                navOptions = navOptions {
                                    launchSingleTop = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
