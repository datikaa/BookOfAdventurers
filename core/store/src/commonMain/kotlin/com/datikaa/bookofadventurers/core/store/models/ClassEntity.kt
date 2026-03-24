package com.datikaa.bookofadventurers.core.store.models

import kotlinx.serialization.Serializable

@Serializable
data class ClassEntity(
    val name: String,
    val selectableSkillCount: Int,
)
