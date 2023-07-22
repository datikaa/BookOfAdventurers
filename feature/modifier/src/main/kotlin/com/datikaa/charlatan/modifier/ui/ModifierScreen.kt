package com.datikaa.charlatan.modifier.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.datikaa.charlatan.core.design.theme.CharlatanTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun ModifierRoute(
    modifier: Modifier = Modifier,
    modifierViewModel: ModifierViewModel = koinViewModel(),
) {
    ModifierScreen(modifier.padding(CharlatanTheme.dimensions.screenPadding),)
}

@Composable
fun ModifierScreen(
    modifier: Modifier = Modifier,
) {

}
