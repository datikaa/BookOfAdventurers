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

        data class Score(
            val modifiableScoreType: KClass<out ModifiableScore>,
            val value: Int
        ) : Type

        data class Proficiency(
            val proficiencyType: KClass<out PossiblyProficient>,
        ) : Type
    }
}

fun List<Modifier.Type>.filterScoreModifiers(
    modifiableScoreType: KClass<out ModifiableScore>
) = this
    .filterIsInstance<Modifier.Type.Score>()
    .filter { it.modifiableScoreType == modifiableScoreType }

fun List<Modifier.Type>.filterProficiencyModifiers(
    proficiencyType: KClass<out PossiblyProficient>,
) = this
    .filterIsInstance<Modifier.Type.Proficiency>()
    .filter { it.proficiencyType == proficiencyType }
