package com.datikaa.boa.modifier.ui

object AddNewModifier {

    data class Modifier(
        val name: String,
        val value: Int?,
        val type: Type,
        val scoreType: ScoreType,
        val ability: Ability,
    )

    enum class Type(val readableName: String) {
        Proficiency("Proficiency modifier"),
        Score("Score modifier"),
    }

    enum class ScoreType(val readableName: String) {
        Ability("Base ability"),
        SavingThrow("Saving throw"),
        Skill("Skill check"),
    }

    sealed interface Ability {

        val readableName: String
        enum class BaseAbility(override val readableName: String) : Ability {
            Strength("Strength score"),
            Dexterity("Dexterity score"),
            Constitution("Constitution score"),
            Intelligence("Intelligence score"),
            Wisdom("Wisdom score"),
            Charisma("Charisma score"),
        }

        enum class SavingThrow(override val readableName: String) : Ability {
            Strength("Strength saving throw"),
            Dexterity("Dexterity saving throw"),
            Constitution("Constitution saving throw"),
            Intelligence("Intelligence saving throw"),
            Wisdom("Wisdom saving throw"),
            Charisma("Charisma saving throw"),
        }

        enum class Skill(override val readableName: String) : Ability {
            Acrobatics("Acrobatics skill"),
            AnimalHandling("Animal handling skill"),
            Arcana("Arcana skill"),
            Athletics("Athletics skill"),
            Deception("Deception skill"),
            History("History skill"),
            Insight("Insight skill"),
            Intimidation("Intimidation skill"),
            Investigation("Investigation skill"),
            Medicine("Medicine skill"),
            Nature("Nature skill"),
            Perception("Perception skill"),
            Performance("Performance skill"),
            Persuasion("Persuasion skill"),
            Religion("Religion skill"),
            SleightOfHand("Sleight of hand skill"),
            Stealth("Stealth skill"),
            Survival("Survival skill"),
        }
    }
}
