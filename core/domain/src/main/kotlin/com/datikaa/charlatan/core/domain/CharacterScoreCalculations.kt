package com.datikaa.charlatan.core.domain

fun Character.calculateAbilityScore(abilityType: AbilityType): Int {
    val baseScore = abilityList.first { it::class == abilityType }.value
    return baseScore + modifiers
        .flatten()
        .flatMap { it.types }
        .filterScoreModifiers(abilityType)
        .sumOf { it.value }
}

fun Character.calculateSavingThrowScore(savingThrow: SavingThrow): Int {
    val abilityScore = calculateAbilityScore(savingThrow.ability)
    val proficiencyScoreBonus = if (proficientIn(savingThrow)) proficiencyScore else 0
    return abilityScore + proficiencyScoreBonus + modifiers
        .flatten()
        .flatMap { it.types }
        .filterScoreModifiers(savingThrow::class)
        .sumOf { it.value }
}

fun Character.calculateSkillScore(skill: Skill): Int {
    val abilityScore = calculateAbilityScore(skill.ability)
    val proficiencyScoreBonus = if (proficientIn(skill)) proficiencyScore else 0
    return abilityScore + proficiencyScoreBonus + modifiers
        .flatten()
        .flatMap { it.types }
        .filterScoreModifiers(skill::class)
        .sumOf { it.value }
}

fun Character.proficientIn(possiblyProficient: PossiblyProficient): Boolean = modifiers
    .flatten()
    .flatMap { it.types }
    .filterProficiencyModifiers(possiblyProficient::class)
    .isNotEmpty()

val Character.proficiencyScore: Int
    get() = (level - 1).floorDiv(4) + 2