package com.datikaa.boa.core.domain

import kotlin.reflect.KClass

sealed interface Modifier {

    val id: Int
    val name: String
    val description: String
    val nestedModifiers: List<Modifier>

    data class Holder(
        override val id: Int,
        override val name: String,
        override val description: String,
        override val nestedModifiers: List<Modifier>,
    ) : Modifier

    data class Score(
        override val id: Int,
        override val name: String,
        override val description: String,
        override val nestedModifiers: List<Modifier>,
        val modifiableScoreType: KClass<out ModifiableScore>,
        val value: Int
    ) : Modifier

    data class Proficiency(
        override val id: Int,
        override val name: String,
        override val description: String,
        override val nestedModifiers: List<Modifier>,
        val proficiencyType: KClass<out PossiblyProficient>,
    ) : Modifier
}

fun List<Modifier>.filterScoreModifiers(
    modifiableScoreType: KClass<out ModifiableScore>
) = this
    .filterIsInstance<Modifier.Score>()
    .filter { it.modifiableScoreType == modifiableScoreType }

fun List<Modifier>.filterProficiencyModifiers(
    proficiencyType: KClass<out PossiblyProficient>,
) = this
    .filterIsInstance<Modifier.Proficiency>()
    .filter { it.proficiencyType == proficiencyType }
