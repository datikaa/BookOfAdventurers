data class Character(
    val id: Int,
    val name: String,
    val abilities: List<Ability>,
    val modifiers: List<Modifier>,
)

val Character.strengthAbility: Int
    get() = calculateAbilityScore(Ability.Strength::class)

val Character.dexterityAbility: Int
    get() = calculateAbilityScore(Ability.Dexterity::class)

val Character.constitutionAbility: Int
    get() = calculateAbilityScore(Ability.Constitution::class)

val Character.intelligenceAbility: Int
    get() = calculateAbilityScore(Ability.Intelligence::class)

val Character.wisdomAbility: Int
    get() = calculateAbilityScore(Ability.Wisdom::class)

val Character.charismaAbility: Int
    get() = calculateAbilityScore(Ability.Charisma::class)

val Character.strengthSavingThrow: Int
    get() = calculateSavingThrowScore(SavingThrow.Strength)

val Character.dexteritySavingThrow: Int
    get() = calculateSavingThrowScore(SavingThrow.Dexterity)

val Character.constitutionSavingThrow: Int
    get() = calculateSavingThrowScore(SavingThrow.Constitution)

val Character.intelligenceSavingThrow: Int
    get() = calculateSavingThrowScore(SavingThrow.Intelligence)

val Character.wisdomSavingThrow: Int
    get() = calculateSavingThrowScore(SavingThrow.Wisdom)

val Character.charismaSavingThrow: Int
    get() = calculateSavingThrowScore(SavingThrow.Charisma)

