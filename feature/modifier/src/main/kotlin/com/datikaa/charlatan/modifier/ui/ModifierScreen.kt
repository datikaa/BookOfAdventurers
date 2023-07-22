package com.datikaa.charlatan.modifier.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel


@Composable
fun ModifierRoute(
    modifier: Modifier = Modifier,
    modifierViewModel: ModifierViewModel = koinViewModel(),
) {
    ModifierScreen(modifier)
}

@Composable
fun ModifierScreen(
    modifier: Modifier = Modifier,
) {

}