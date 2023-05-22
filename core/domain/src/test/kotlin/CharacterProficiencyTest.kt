import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.core.domain.proficiencyScore
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.Test

class CharacterProficiencyTest {

    @Test
    fun testProficiencyValues() {
        createTestCharacter(level = 1).proficiencyScore shouldBeExactly 2
        createTestCharacter(level = 2).proficiencyScore shouldBeExactly 2
        createTestCharacter(level = 3).proficiencyScore shouldBeExactly 2
        createTestCharacter(level = 4).proficiencyScore shouldBeExactly 2
        createTestCharacter(level = 5).proficiencyScore shouldBeExactly 3
        createTestCharacter(level = 6).proficiencyScore shouldBeExactly 3
        createTestCharacter(level = 7).proficiencyScore shouldBeExactly 3
        createTestCharacter(level = 8).proficiencyScore shouldBeExactly 3
        createTestCharacter(level = 9).proficiencyScore shouldBeExactly 4
        createTestCharacter(level = 10).proficiencyScore shouldBeExactly 4
        createTestCharacter(level = 20).proficiencyScore shouldBeExactly 6
    }

    private fun createTestCharacter(level: Int) = Character(
        id = 0,
        level = level,
        name = "Rondell",
        abilityList = listOf(),
        modifiers = listOf()
    )
}