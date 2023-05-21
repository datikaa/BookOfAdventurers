data class Character(
    val name: String,
    val abilities: List<Ability>,
    val modifiers: List<Modifier>,
)

fun Character.calculateAbilityScore(abilityType: AbilityType): Int {
    val baseScore = abilities.first { it::class == abilityType }.value
    return baseScore + modifiers
        .flatten()
        .flatMap { it.attributeModifiers }
        .filterModifiableAttributeType(abilityType)
        .sumOf { it.value }
}

fun Character.calculateSavingThrowScore(savingThrow: SavingThrow): Int {
    val abilityScore = calculateAbilityScore(savingThrow.ability)
    return abilityScore + modifiers
        .flatten()
        .flatMap { it.attributeModifiers }
        .filterModifiableAttributeType(savingThrow::class)
        .sumOf { it.value }
}
