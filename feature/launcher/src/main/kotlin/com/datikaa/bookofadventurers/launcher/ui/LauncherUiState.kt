package com.datikaa.bookofadventurers.launcher.ui

import androidx.compose.runtime.Immutable

@Immutable
data class LauncherUiState(
    val characters: List<CharacterListItem>,
    val classes: List<ClassListItem>,
    val backgrounds: List<BackgroundListItem>,
    val modifiers: List<ModifiersListItem>,
) {

    @Immutable
    data class CharacterListItem(
        val id: Int,
        val name: String,
    )

    @Immutable
    data class BackgroundListItem(
        val id: Int,
        val name: String,
    )

    @Immutable
    data class ClassListItem(
        val id: Int,
        val name: String,
    )

    @Immutable
    data class ModifiersListItem(
        val id: Int,
        val name: String,
    )
}
