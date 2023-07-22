package com.datikaa.charlatan.launcher.ui

import androidx.compose.runtime.Immutable

@Immutable
data class LauncherUiState(
    val characters: List<CharacterListItem>,
    val modifiers: List<ModifiersListItem>,
) {

    @Immutable
    data class CharacterListItem(
        val id: Int,
        val name: String,
    )

    @Immutable
    data class ModifiersListItem(
        val id: Int,
        val name: String,
    )
}
