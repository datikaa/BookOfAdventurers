package com.datikaa.charlatan.core.domain

import kotlin.reflect.KClass

fun Character.sumOfModifiersFor(modifiableScoreType: KClass<out ModifiableScore>): Int = modifiers
    .flatten()
    .filterScoreModifiers(modifiableScoreType)
    .sumOf { it.value }

fun Character.calculateAbilityScore(abilityType: AbilityType): Int {
    val baseScore = abilityList.first { it::class == abilityType }.value
    val modifierScore = sumOfModifiersFor(abilityType)
    return (baseScore + modifierScore - 10).floorDiv(2)
}

fun <T> Character.calculateCalculatedScore(calculatedScore: T): Int where T : CalculatedScore, T : PossiblyProficient {
    val abilityScore = calculateAbilityScore(calculatedScore.baseAbility)
    val modifierScore = sumOfModifiersFor(calculatedScore::class)
    val proficiencyScoreBonus = if (proficientIn(calculatedScore)) proficiencyScore else 0
    return abilityScore + proficiencyScoreBonus + modifierScore
}

fun Character.calculateSavingThrowScore(savingThrow: SavingThrow): Int =
    calculateCalculatedScore(savingThrow)

fun Character.calculateSkillCheckScore(skill: Skill): Int =
    calculateCalculatedScore(skill)

fun Character.proficientIn(possiblyProficient: PossiblyProficient): Boolean = modifiers
    .flatten()
    .filterProficiencyModifiers(possiblyProficient::class)
    .isNotEmpty()

val Character.proficiencyScore: Int
    get() = (level - 1).floorDiv(4) + 2