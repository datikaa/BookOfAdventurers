package com.datikaa.bookofadventurers.core.domain

data class BoaClass(
    val id: Long,
    val name: String,
    val selectableSkillCount: Int,
    val savingThrowProficiencies: List<Modifier>,
    val selectableSkillProficiencies: List<Modifier>,
)
