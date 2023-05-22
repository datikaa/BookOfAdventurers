package com.datikaa.charlatan.core.domain

fun Character.calculateAbilityScore(abilityType: AbilityType): Int {
    val baseScore = abilityList.first { it::class == abilityType }.value
    return baseScore + modifiers
        .flatten()
        .flatMap { it.scoreModifiers }
        .filterModifiableAttributeType(abilityType)
        .sumOf { it.value }
}

fun Character.calculateSavingThrowScore(savingThrow: SavingThrow): Int {
    val abilityScore = calculateAbilityScore(savingThrow.ability)
    return abilityScore + modifiers
        .flatten()
        .flatMap { it.scoreModifiers }
        .filterModifiableAttributeType(savingThrow::class)
        .sumOf { it.value }
}

fun Character.calculateSkillScore(skill: Skill): Int {
    val abilityScore = calculateAbilityScore(skill.ability)
    return abilityScore + modifiers
        .flatten()
        .flatMap { it.scoreModifiers }
        .filterModifiableAttributeType(skill::class)
        .sumOf { it.value }
}

val Character.proficiencyScore: Int
    get() = (level - 1).floorDiv(4) + 2