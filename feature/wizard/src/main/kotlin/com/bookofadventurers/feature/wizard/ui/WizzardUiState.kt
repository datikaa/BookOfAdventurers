package com.bookofadventurers.feature.wizard.ui

import androidx.compose.runtime.Immutable

@Immutable
data class WizardUiState(
    val dialog: Dialog? = null,
    val selectableClasses: List<ClassItem>,
) {

    @Immutable
    data class Dialog(
        val classId: Long,
        val selectableCount: Int,
    )

    @Immutable
    data class Modifier(
        val id: Int,
        val name: String,
        val selected: Boolean,
        val selectable: Boolean,
    )

    @Immutable
    data class ClassItem(
        val id: Long,
        val name: String,
        val selectableCount: Int,
        val savingThrowProficiencyModifiers: List<Modifier>,
        val selectableSkillProficiencyModifiers: List<Modifier>,
        val selected: Boolean,
    )
}