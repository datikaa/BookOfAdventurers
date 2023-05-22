package com.datikaa.charlatan.core.domain

import kotlin.reflect.KClass

data class Modifier(
    val id: Int,
    val name: String,
    val description: String,
    val scoreModifiers: List<ScoreModifier>,
    val nestedModifiers: List<Modifier>,
) {
    data class ScoreModifier(
        val modifiableScoreType: KClass<out ModifiableScore>,
        val value: Int,
    )
}

fun List<Modifier.ScoreModifier>.filterModifiableAttributeType(
    modifiableScoreType: KClass<out ModifiableScore>
) = filter { it.modifiableScoreType == modifiableScoreType }