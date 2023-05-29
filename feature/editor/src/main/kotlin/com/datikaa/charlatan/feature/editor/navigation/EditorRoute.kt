package com.datikaa.charlatan.feature.editor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.datikaa.charlatan.feature.editor.ui.EditorRoute

const val editorRoute = "editor_route"

fun NavController.navigateToEditor(navOptions: NavOptions? = null) {
    this.navigate(editorRoute, navOptions)
}

fun NavGraphBuilder.editorScreen() {
    composable(route = editorRoute) {
        EditorRoute()
    }
}