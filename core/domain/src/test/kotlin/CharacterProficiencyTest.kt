import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.charlatan.core.domain.SavingThrow
import com.datikaa.charlatan.core.domain.proficiencyScore
import com.datikaa.charlatan.core.domain.proficientIn
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.Test

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

    private fun createScoreTestingCharacter(level: Int) = Character(
        id = 0,
        level = level,
        name = "Rondell",
        abilityList = listOf(),
        modifiers = listOf(

        )
    )

    private val dexSavingThrowProficiencyModifier = Modifier(
        id = 0,
        name = "",
        description = "",
        types = listOf(
            Modifier.Type.Proficiency(SavingThrow.Dexterity::class)
        ),
        nestedModifiers = listOf(),
    )

    private val modifierTestingCharacter = Character(
        id = 0,
        level = 10,
        name = "Rondell",
        abilityList = listOf(),
        modifiers = listOf(
            dexSavingThrowProficiencyModifier,
        )
    )
}