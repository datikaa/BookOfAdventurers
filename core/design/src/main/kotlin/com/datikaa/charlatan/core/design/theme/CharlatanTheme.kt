package com.datikaa.charlatan.core.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import com.datikaa.charlatan.core.design.m3.CharlatanM3Theme


@Composable
fun CharlatanTheme(
    dimensions: CharlatanDimensions = CharlatanTheme.dimensions,
    content: @Composable () -> Unit
) {
    CharlatanM3Theme {
        CompositionLocalProvider(
            LocalCharlatanDimensions provides dimensions,
        ) {
            content()
        }
    }
}

object CharlatanTheme {

    val dimensions: CharlatanDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalCharlatanDimensions.current
}

object CharlatanDimensions {
    val screenPadding = 4.dp
    val cardSpacing = 4.dp
}

val LocalCharlatanDimensions = staticCompositionLocalOf { CharlatanDimensions }