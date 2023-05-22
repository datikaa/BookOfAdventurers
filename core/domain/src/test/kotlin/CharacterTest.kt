import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.Test

class CharacterTest {

    @Test
    fun testModifierFlattening() {
        val flattenModifiers = testCharacter.modifiers.flatten()
        flattenModifiers.size shouldBeExactly 4
        plus1DexModifier shouldBeIn flattenModifiers
        plus1ConModifier shouldBeIn flattenModifiers
        plus1DexAndPlus1ConModifierCollection shouldBeIn flattenModifiers
        plus1DexSavingThrowModifier shouldBeIn flattenModifiers
    }

    @Test
    fun testAbilityScoreCalculations() {
        testCharacter.strengthAbility shouldBeExactly 10
        testCharacter.dexterityAbility shouldBeExactly 12
        testCharacter.constitutionAbility shouldBeExactly 13
        testCharacter.intelligenceAbility shouldBeExactly 13
        testCharacter.wisdomAbility shouldBeExactly 14
        testCharacter.charismaAbility shouldBeExactly 15
    }

    @Test
    fun testSavingThrowCalculation() {
        testCharacter.strengthSavingThrow shouldBeExactly 10
        testCharacter.dexteritySavingThrow shouldBeExactly 13
        testCharacter.constitutionSavingThrow shouldBeExactly 13
        testCharacter.intelligenceSavingThrow shouldBeExactly 13
        testCharacter.wisdomSavingThrow shouldBeExactly 14
        testCharacter.charismaSavingThrow shouldBeExactly 15
    }
}

private val plus1DexModifier = Modifier(
    id = 0,
    name = "Adds +1 Dex",
    description = "",
    scoreModifiers = listOf(
        Modifier.ScoreModifier(Ability.Dexterity::class, 1),
    ),
    nestedModifiers = listOf()
)

private val plus1ConModifier = Modifier(
    id = 0,
    name = "Adds +1 Con",
    description = "",
    scoreModifiers = listOf(
        Modifier.ScoreModifier(Ability.Constitution::class, 1),
    ),
    nestedModifiers = listOf()
)

private val plus1DexAndPlus1ConModifierCollection = Modifier(
    id = 0,
    name = "Collection of 2 modifiers without own attribute modifier",
    description = "",
    scoreModifiers = listOf(),
    nestedModifiers = listOf(
        plus1DexModifier,
        plus1ConModifier,
    )
)

private val plus1DexSavingThrowModifier = Modifier(
    id = 0,
    name = "Dex SaveThrow Attribute modifier",
    description = "",
    scoreModifiers = listOf(
        Modifier.ScoreModifier(SavingThrow.Dexterity::class, 1)
    ),
    nestedModifiers = emptyList()
)

private val testCharacter = Character(
    id = 0,
    name = "Rondell",
    abilities = listOf(
        Ability.Strength(value = 10),
        Ability.Dexterity(value = 11),
        Ability.Constitution(value = 12),
        Ability.Intelligence(value = 13),
        Ability.Wisdom(value = 14),
        Ability.Charisma(value = 15),
    ),
    modifiers = listOf(
        plus1DexAndPlus1ConModifierCollection,
        plus1DexSavingThrowModifier,
    )
)