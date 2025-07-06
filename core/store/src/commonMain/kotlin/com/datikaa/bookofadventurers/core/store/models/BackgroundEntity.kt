package com.datikaa.bookofadventurers.core.store.models

import kotlinx.serialization.Serializable

@Serializable
data class BackgroundEntity(
    val name: String,
    val featureTitle: String,
    val featureDescription: String,
    val suggestedCharacteristics: String,
)
