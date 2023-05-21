import kotlin.reflect.KClass

typealias AbilityType = KClass<out Ability>

sealed interface Ability : ModifiableScore {

    val value: Int

    data class Strength(
        override val value: Int
    ) : Ability

    data class Dexterity(
        override val value: Int
    ) : Ability

    data class Constitution(
        override val value: Int
    ) : Ability

    data class Intelligence(
        override val value: Int
    ) : Ability

    data class Wisdom(
        override val value: Int
    ) : Ability

    data class Charisma(
        override val value: Int
    ) : Ability
}
