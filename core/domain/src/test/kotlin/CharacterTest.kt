import io.kotest.matchers.ints.shouldBeExactly
import org.junit.Test

class CharacterTest {

    @Test
    fun testModifierFlattening() {
        val flattenModifiers = testCharacter.modifiers.flatten()
        flattenModifiers.size shouldBeExactly 4
    }

    @Test
    fun testAbilityScoreCalculation() {
        val strength = testCharacter.calculateAbilityScore(Ability.Strength::class)
        strength shouldBeExactly 11
        val intelligence = testCharacter.calculateAbilityScore(Ability.Intelligence::class)
        intelligence shouldBeExactly 10
    }

    @Test
    fun testSavingThrowCalculation() {
        val strength = testCharacter.calculateSavingThrowScore(SavingThrow.Strength)
        strength shouldBeExactly 11
        val dexterity = testCharacter.calculateSavingThrowScore(SavingThrow.Dexterity)
        dexterity shouldBeExactly 12
    }
}

private val testCharacter = Character(
    name = "Rondell",
    abilities = listOf(
        Ability.Strength(value = 10),
        Ability.Dexterity(value = 10),
        Ability.Constitution(value = 10),
        Ability.Intelligence(value = 10),
        Ability.Wisdom(value = 10),
        Ability.Charisma(value = 10),
    ),
    modifiers = listOf(
        Modifier(
            name = "Adds +1 to Dex and Str abilities with nested Modifiers",
            description = "",
            attributeModifiers = listOf(),
            nestedModifiers = listOf(
                Modifier(
                    name = "",
                    description = "",
                    attributeModifiers = listOf(
                        Modifier.AttributeModifier(Ability.Strength::class, 1),
                    ),
                    nestedModifiers = listOf()
                ),
                Modifier(
                    name = "",
                    description = "",
                    attributeModifiers = listOf(
                        Modifier.AttributeModifier(Ability.Dexterity::class, 1),
                    ),
                    nestedModifiers = listOf()
                ),
            )
        ),
        Modifier(
            name = "Adds +1 to Dex Saving Throw",
            description = "",
            attributeModifiers = listOf(
                Modifier.AttributeModifier(SavingThrow.Dexterity::class, 1)
            ),
            nestedModifiers = emptyList()
        )
    )
)