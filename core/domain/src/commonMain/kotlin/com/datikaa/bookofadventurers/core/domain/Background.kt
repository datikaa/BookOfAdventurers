package com.datikaa.bookofadventurers.core.domain

data class Background(
    val id: Long,
    val name: String,
    val featureTitle: String,
    val featureDescription: String,
    val suggestedCharacteristics: String,
    val skillProficiencies: List<Modifier>,
)
