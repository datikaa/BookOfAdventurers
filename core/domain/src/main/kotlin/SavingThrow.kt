import kotlin.reflect.KClass

typealias SavingThrowType = KClass<out SavingThrow>

sealed class SavingThrow(val ability: AbilityType) : ModifiableAttribute {
    object Strength : SavingThrow(ability = Ability.Strength::class)
    object Dexterity : SavingThrow(ability = Ability.Dexterity::class)
    object Constitution : SavingThrow(ability = Ability.Constitution::class)
    object Intelligence : SavingThrow(ability = Ability.Intelligence::class)
    object Wisdom : SavingThrow(ability = Ability.Wisdom::class)
    object Charisma : SavingThrow(ability = Ability.Charisma::class)
}