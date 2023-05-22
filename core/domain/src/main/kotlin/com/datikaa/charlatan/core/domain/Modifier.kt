package com.datikaa.charlatan.core.domain

import kotlin.reflect.KClass

data class Modifier(
    val id: Int,
    val name: String,
    val description: String,
    val types: List<Type>,
    val nestedModifiers: List<Modifier>,
) {
    sealed interface Type {
        val modifiableScoreType: KClass<out ModifiableScore>
        data class Score(
            override val modifiableScoreType: KClass<out ModifiableScore>,
            val value: Int
        ): Type

        data class Proficiency(
            override val modifiableScoreType: KClass<out ModifiableScore>,
        ): Type
    }
}

fun List<Modifier.Type>.filterScoreModifiers(
    modifiableScoreType: KClass<out ModifiableScore>
) = filter { it.modifiableScoreType == modifiableScoreType }.filterIsInstance<Modifier.Type.Score>()