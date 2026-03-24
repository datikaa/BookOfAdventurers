package com.datikaa.bookofadventurers.core.store.models

import kotlinx.serialization.Serializable

@Serializable
data class AbilityEntity(
    val type: Type,
    val value: Int,
) {
    @Serializable
    enum class Type {
        Strength,
        Dexterity,
        Constitution,
        Intelligence,
        Wisdom,
        Charisma,
    }
}
