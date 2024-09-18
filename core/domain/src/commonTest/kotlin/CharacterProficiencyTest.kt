import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass
import com.datikaa.bookofadventurers.core.domain.Modifier
import com.datikaa.bookofadventurers.core.domain.SavingThrow
import com.datikaa.bookofadventurers.core.domain.calculateSavingThrowScore
import com.datikaa.bookofadventurers.core.domain.proficiencyScore
import com.datikaa.bookofadventurers.core.domain.proficientIn
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class CharacterProficiencyTest {

    @Test
    fun testProficiencyValues() {
        createScoreTestingCharacter(level = 1).proficiencyScore shouldBeExactly 2
        createScoreTestingCharacter(level = 2).proficiencyScore shouldBeExactly 2
        createScoreTestingCharacter(level = 3).proficiencyScore shouldBeExactly 2
        createScoreTestingCharacter(level = 4).proficiencyScore shouldBeExactly 2
        createScoreTestingCharacter(level = 5).proficiencyScore shouldBeExactly 3
        createScoreTestingCharacter(level = 6).proficiencyScore shouldBeExactly 3
        createScoreTestingCharacter(level = 7).proficiencyScore shouldBeExactly 3
        createScoreTestingCharacter(level = 8).proficiencyScore shouldBeExactly 3
        createScoreTestingCharacter(level = 9).proficiencyScore shouldBeExactly 4
        createScoreTestingCharacter(level = 10).proficiencyScore shouldBeExactly 4
        createScoreTestingCharacter(level = 11).proficiencyScore shouldBeExactly 4
        createScoreTestingCharacter(level = 12).proficiencyScore shouldBeExactly 4
        createScoreTestingCharacter(level = 13).proficiencyScore shouldBeExactly 5
        createScoreTestingCharacter(level = 14).proficiencyScore shouldBeExactly 5
        createScoreTestingCharacter(level = 15).proficiencyScore shouldBeExactly 5
        createScoreTestingCharacter(level = 16).proficiencyScore shouldBeExactly 5
        createScoreTestingCharacter(level = 17).proficiencyScore shouldBeExactly 6
        createScoreTestingCharacter(level = 18).proficiencyScore shouldBeExactly 6
        createScoreTestingCharacter(level = 19).proficiencyScore shouldBeExactly 6
        createScoreTestingCharacter(level = 20).proficiencyScore shouldBeExactly 6
    }

    @Test
    fun testFindingProficiency() {
        modifierTestingCharacter.proficientIn(SavingThrow.Dexterity) shouldBe true
        modifierTestingCharacter.proficientIn(SavingThrow.Strength) shouldBe false
    }

    @Test
    fun testScoresAffectedByProficiencyModifiers() {
        modifierTestingCharacter.calculateSavingThrowScore(SavingThrow.Dexterity) shouldBeExactly 4
        modifierTestingCharacter.calculateSavingThrowScore(SavingThrow.Strength) shouldBeExactly 0
    }

    private val emptyBackground = Background(
        id = 0,
        name = "",
        featureTitle = "",
        featureDescription = "",
        suggestedCharacteristics = "",
        skillProficiencies = listOf(),
    )

    private val emptyClass = CharacterClass(
        id = 0L,
        name = "",
        savingThrowProficiencies = listOf(),
        skillProficiencies = emptyList(),
    )

    private fun createScoreTestingCharacter(level: Int) = BoaCharacter(
        id = 0,
        level = level,
        characterBackground = emptyBackground,
        characterClass = emptyClass,
        name = "Rondell",
        abilityList = listOf(),
        modifiers = listOf(),
    )

    private val dexSavingThrowProficiencyModifier = Modifier.Proficiency(
        id = 0,
        name = "",
        description = "",
        proficiencyType = SavingThrow.Dexterity::class,
        nestedModifiers = listOf(),
    )

    private val modifierTestingCharacter = BoaCharacter(
        id = 0,
        level = 10,
        characterBackground = emptyBackground,
        characterClass = emptyClass,
        name = "Rondell",
        abilityList = listOf(
            Ability.Strength(value = 10),
            Ability.Dexterity(value = 10),
        ),
        modifiers = listOf(
            dexSavingThrowProficiencyModifier,
        )
    )
}
