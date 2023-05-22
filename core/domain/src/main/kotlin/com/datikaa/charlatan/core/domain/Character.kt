package com.datikaa.charlatan.core.domain

data class Character(
    val id: Int,
    val name: String,
    val abilityList: List<Ability>,
    val modifiers: List<Modifier>,
)