package com.datikaa.bookofadventurers.core.store.models

import kotlinx.serialization.Serializable

@Serializable
data class ClassEntity(
    val id: Long,
    val name: String,
)
