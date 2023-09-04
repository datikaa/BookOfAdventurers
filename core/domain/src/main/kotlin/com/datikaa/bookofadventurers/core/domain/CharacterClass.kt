package com.datikaa.bookofadventurers.core.domain

data class CharacterClass(
    val id: Long,
    val name: String,
    val savingThrowProficiencies: List<Modifier>,
    val skillProficiencies: List<Modifier>,
)