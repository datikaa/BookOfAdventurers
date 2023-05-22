import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.charlatan.core.domain.SavingThrow
import com.datikaa.charlatan.core.domain.Skill
import com.datikaa.charlatan.core.domain.calculateAbilityScore
import com.datikaa.charlatan.core.domain.calculateSavingThrowScore
import com.datikaa.charlatan.core.domain.calculateSkillScore
import com.datikaa.charlatan.core.domain.flatten
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.Test

class CharacterModifiersTest {

    @Test
    fun testModifierFlattening() {
        val flattenModifiers = testCharacter.modifiers.flatten()
        flattenModifiers.size shouldBeExactly 5
        plus1DexModifier shouldBeIn flattenModifiers
        plus1ConModifier shouldBeIn flattenModifiers
        plus1DexAndPlus1ConModifierCollection shouldBeIn flattenModifiers
        plus1DexSavingThrowModifier shouldBeIn flattenModifiers
    }

    @Test
    fun testAbilityScoreCalculations() {
        testCharacter.calculateAbilityScore(Ability.Strength::class) shouldBeExactly 10
        testCharacter.calculateAbilityScore(Ability.Dexterity::class) shouldBeExactly 12
        testCharacter.calculateAbilityScore(Ability.Constitution::class) shouldBeExactly 13
        testCharacter.calculateAbilityScore(Ability.Intelligence::class) shouldBeExactly 13
        testCharacter.calculateAbilityScore(Ability.Wisdom::class) shouldBeExactly 14
        testCharacter.calculateAbilityScore(Ability.Charisma::class) shouldBeExactly 15
    }

    @Test
    fun testSavingThrowCalculation() {
        testCharacter.calculateSavingThrowScore(SavingThrow.Strength) shouldBeExactly 10
        testCharacter.calculateSavingThrowScore(SavingThrow.Dexterity) shouldBeExactly 13
        testCharacter.calculateSavingThrowScore(SavingThrow.Constitution) shouldBeExactly 13
        testCharacter.calculateSavingThrowScore(SavingThrow.Intelligence) shouldBeExactly 13
        testCharacter.calculateSavingThrowScore(SavingThrow.Wisdom) shouldBeExactly 14
        testCharacter.calculateSavingThrowScore(SavingThrow.Charisma) shouldBeExactly 15
    }

    @Test
    fun testSkillCalculation() {
        testCharacter.calculateSkillScore(Skill.Athletics) shouldBeExactly 10
        testCharacter.calculateSkillScore(Skill.Arcana) shouldBeExactly 13
        testCharacter.calculateSkillScore(Skill.Stealth) shouldBeExactly 13
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

private val plus1StealthSkillModifier = Modifier(
    id = 0,
    name = "Adds +1 to Stealth skill",
    description = "",
    scoreModifiers = listOf(
        Modifier.ScoreModifier(Skill.Stealth::class, 1)
    ),
    nestedModifiers = emptyList()
)

private val testCharacter = Character(
    id = 0,
    level = 1,
    name = "Rondell",
    abilityList = listOf(
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
        plus1StealthSkillModifier
    )
)