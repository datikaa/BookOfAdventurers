import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.core.domain.proficiencyScore
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.Test

class CharacterProficiencyTest {

    @Test
    fun testProficiencyValues() {
        level1Character.proficiencyScore shouldBeExactly 2
        level2Character.proficiencyScore shouldBeExactly 2
        level3Character.proficiencyScore shouldBeExactly 2
        level4Character.proficiencyScore shouldBeExactly 2
        level5Character.proficiencyScore shouldBeExactly 3
        level6Character.proficiencyScore shouldBeExactly 3
        level7Character.proficiencyScore shouldBeExactly 3
        level8Character.proficiencyScore shouldBeExactly 3
        level9Character.proficiencyScore shouldBeExactly 4
        level10Character.proficiencyScore shouldBeExactly 4
        level20Character.proficiencyScore shouldBeExactly 6
    }

    private val level1Character = Character(
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
        modifiers = listOf()
    )
    private val level2Character = level1Character.copy(level = 2)
    private val level3Character = level1Character.copy(level = 3)
    private val level4Character = level1Character.copy(level = 4)
    private val level5Character = level1Character.copy(level = 5)
    private val level6Character = level1Character.copy(level = 6)
    private val level7Character = level1Character.copy(level = 7)
    private val level8Character = level1Character.copy(level = 8)
    private val level9Character = level1Character.copy(level = 9)
    private val level10Character = level1Character.copy(level = 10)
    private val level20Character = level1Character.copy(level = 20)
}