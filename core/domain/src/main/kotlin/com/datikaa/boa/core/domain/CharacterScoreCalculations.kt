package com.datikaa.boa.core.domain

import kotlin.reflect.KClass

fun BoaCharacter.sumOfModifiersFor(modifiableScoreType: KClass<out ModifiableScore>): Int = modifiers
    .flatten()
    .filterScoreModifiers(modifiableScoreType)
    .sumOf { it.value }

fun BoaCharacter.calculateAbilityScore(abilityType: AbilityType): Int {
    val baseScore = abilityList.first { it::class == abilityType }.value
    val modifierScore = sumOfModifiersFor(abilityType)
    return (baseScore + modifierScore - 10).floorDiv(2)
}

fun <T> BoaCharacter.calculateCalculatedScore(calculatedScore: T): Int where T : CalculatedScore, T : PossiblyProficient {
    val abilityScore = calculateAbilityScore(calculatedScore.baseAbility)
    val modifierScore = sumOfModifiersFor(calculatedScore::class)
    val proficiencyScoreBonus = if (proficientIn(calculatedScore)) proficiencyScore else 0
    return abilityScore + proficiencyScoreBonus + modifierScore
}

fun BoaCharacter.calculateSavingThrowScore(savingThrow: SavingThrow): Int =
    calculateCalculatedScore(savingThrow)

fun BoaCharacter.calculateSkillCheckScore(skill: Skill): Int =
    calculateCalculatedScore(skill)

fun BoaCharacter.proficientIn(possiblyProficient: PossiblyProficient): Boolean = modifiers
    .flatten()
    .filterProficiencyModifiers(possiblyProficient::class)
    .isNotEmpty()

val BoaCharacter.proficiencyScore: Int
    get() = (level - 1).floorDiv(4) + 2
