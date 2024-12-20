import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass
import com.datikaa.bookofadventurers.core.domain.Modifier
import com.datikaa.bookofadventurers.core.domain.SavingThrow
import com.datikaa.bookofadventurers.core.domain.Skill
import com.datikaa.bookofadventurers.core.domain.calculateAbilityScore
import com.datikaa.bookofadventurers.core.domain.calculateSavingThrowScore
import com.datikaa.bookofadventurers.core.domain.calculateSkillCheckScore
import com.datikaa.bookofadventurers.core.domain.flatten
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.ints.shouldBeExactly
import kotlin.test.Test

class ComplexCharacterModifiersTest {

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
        testCharacter.calculateAbilityScore(Ability.Strength::class) shouldBeExactly -1
        testCharacter.calculateAbilityScore(Ability.Dexterity::class) shouldBeExactly 1
        testCharacter.calculateAbilityScore(Ability.Constitution::class) shouldBeExactly 1
        testCharacter.calculateAbilityScore(Ability.Intelligence::class) shouldBeExactly 1
        testCharacter.calculateAbilityScore(Ability.Wisdom::class) shouldBeExactly 2
        testCharacter.calculateAbilityScore(Ability.Charisma::class) shouldBeExactly 2
    }

    @Test
    fun testSavingThrowCalculation() {
        testCharacter.calculateSavingThrowScore(SavingThrow.Strength) shouldBeExactly 3
        testCharacter.calculateSavingThrowScore(SavingThrow.Dexterity) shouldBeExactly 2
        testCharacter.calculateSavingThrowScore(SavingThrow.Constitution) shouldBeExactly 1
        testCharacter.calculateSavingThrowScore(SavingThrow.Intelligence) shouldBeExactly 1
        testCharacter.calculateSavingThrowScore(SavingThrow.Wisdom) shouldBeExactly 2
        testCharacter.calculateSavingThrowScore(SavingThrow.Charisma) shouldBeExactly 2
    }

    @Test
    fun testSkillCalculation() {
        testCharacter.calculateSkillCheckScore(Skill.Acrobatics) shouldBeExactly 1
        testCharacter.calculateSkillCheckScore(Skill.AnimalHandling) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Arcana) shouldBeExactly 5
        testCharacter.calculateSkillCheckScore(Skill.Athletics) shouldBeExactly -1
        testCharacter.calculateSkillCheckScore(Skill.Deception) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.History) shouldBeExactly 1
        testCharacter.calculateSkillCheckScore(Skill.Insight) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Intimidation) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Investigation) shouldBeExactly 1
        testCharacter.calculateSkillCheckScore(Skill.Medicine) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Nature) shouldBeExactly 1
        testCharacter.calculateSkillCheckScore(Skill.Perception) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Performance) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Persuasion) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Religion) shouldBeExactly 5
        testCharacter.calculateSkillCheckScore(Skill.SleightOfHand) shouldBeExactly 1
        testCharacter.calculateSkillCheckScore(Skill.Stealth) shouldBeExactly 2
        testCharacter.calculateSkillCheckScore(Skill.Survival) shouldBeExactly 2

    }
}

private val plus1DexModifier = Modifier.Score(
    id = 0,
    name = "Adds +1 Dex",
    description = "",
    modifiableScoreType = Ability.Dexterity::class,
    value = 1,
    nestedModifiers = listOf(),
)

private val plus1ConModifier = Modifier.Score(
    id = 0,
    name = "Adds +1 Con",
    description = "",
    modifiableScoreType = Ability.Constitution::class,
    value = 1,
    nestedModifiers = listOf()
)

private val plus1DexAndPlus1ConModifierCollection = Modifier.Holder(
    id = 0,
    name = "Collection of 2 modifiers without own ability modifier",
    description = "",
    nestedModifiers = listOf(
        plus1DexModifier,
        plus1ConModifier,
    )
)

private val plus1DexSavingThrowModifier = Modifier.Score(
    id = 0,
    name = "Dex SaveThrow Ability modifier",
    description = "",
    modifiableScoreType = SavingThrow.Dexterity::class,
    value = 1,
    nestedModifiers = emptyList()
)

private val plus1StealthSkillModifier = Modifier.Score(
    id = 0,
    name = "Adds +1 to Stealth skill",
    description = "",
    modifiableScoreType = Skill.Stealth::class,
    value = 1,
    nestedModifiers = emptyList()
)

private val arcanaSkillCheckProficiencyModifier = Modifier.Proficiency(
    id = 0,
    name = "Adds proficiency to Arcana skill",
    description = "",
    proficiencyType = Skill.Arcana::class,
    nestedModifiers = emptyList()
)

private val religionSkillCheckProficiencyModifier = Modifier.Proficiency(
    id = 0,
    name = "Adds proficiency to Religion skill",
    description = "",
    proficiencyType = Skill.Religion::class,
    nestedModifiers = emptyList()
)

private val strengthSavingThrowProficiencyModifier = Modifier.Proficiency(
    id = 0,
    name = "Adds proficiency to Strength saving throws",
    description = "",
    proficiencyType = SavingThrow.Strength::class,
    nestedModifiers = emptyList()
)

private val testCharacter = BoaCharacter(
    id = 0,
    level = 10, // +4 proficiency
    name = "Rondell",
    abilityList = listOf(
        Ability.Strength(value = 9), // -1
        Ability.Dexterity(value = 11), // 0
        Ability.Constitution(value = 12), // +1
        Ability.Intelligence(value = 13), // +1
        Ability.Wisdom(value = 14), // +2
        Ability.Charisma(value = 15), // +2
    ),
    characterBackground = Background(
        id = 0,
        name = "Test",
        featureTitle = "",
        featureDescription = "",
        suggestedCharacteristics = "",
        skillProficiencies = listOf(
            arcanaSkillCheckProficiencyModifier
        ),

    ),
    characterClass = CharacterClass(
        id = 0,
        name = "Test",
        savingThrowProficiencies = listOf(
            strengthSavingThrowProficiencyModifier,
        ),
        skillProficiencies = listOf(
            religionSkillCheckProficiencyModifier
        ),
    ),
    modifiers = listOf(
        plus1DexAndPlus1ConModifierCollection,
        plus1DexSavingThrowModifier,
        plus1StealthSkillModifier,
    )
)
