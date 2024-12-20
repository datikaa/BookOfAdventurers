package com.datikaa.bookofadventurers.core.database.prefill

import kotlinx.serialization.Serializable

@Serializable
data class Background(
    val name: String,
    val skillProficiencies: List<Int>,
    val featureTitle: String,
    val featureDescription: String,
    val suggestedCharacteristics: String,
)

@Serializable
data class Class(
    val name: String,
    val selectableSkillCount: Int,
    val savingThrows: List<Int>,
    val skills: List<Int>,
)

@Serializable
data class Modifier(
    val name: String,
    val description: String,
    val type: String,
    val modifiableScoreType: String,
    val modifierValue: Int? = null,
)
