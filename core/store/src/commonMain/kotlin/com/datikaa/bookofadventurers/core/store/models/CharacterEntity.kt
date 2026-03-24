package com.datikaa.bookofadventurers.core.store.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterEntity(
    val id: Int,
    val name: String,
    val level: Int,
    val backgroundEntity: BackgroundEntity,
    val classEntity: ClassEntity,
    val abilities: List<AbilityEntity>,
    val modifiers: List<ModifierEntity>,
)
