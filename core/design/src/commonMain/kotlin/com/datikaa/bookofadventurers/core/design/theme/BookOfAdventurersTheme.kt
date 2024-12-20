package com.datikaa.bookofadventurers.core.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import com.datikaa.bookofadventurers.core.design.m3.BookOfAdventurersM3Theme


@Composable
fun BookOfAdventurersTheme(
    dimensions: BoaDimensions = BookOfAdventurersTheme.dimensions,
    content: @Composable () -> Unit
) {
    BookOfAdventurersM3Theme {
        CompositionLocalProvider(
            LocalBoaDimensions provides dimensions,
        ) {
            content()
        }
    }
}

object BookOfAdventurersTheme {

    val dimensions: BoaDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalBoaDimensions.current
}

object BoaDimensions {
    val screenPadding = 4.dp
    val cardSpacing = 4.dp
}

val LocalBoaDimensions = staticCompositionLocalOf { BoaDimensions }
