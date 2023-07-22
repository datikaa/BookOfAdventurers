package com.datikaa.charlatan.modifier.domain

import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.ModifiableScore
import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.charlatan.core.domain.PossiblyProficient
import com.datikaa.charlatan.core.domain.SavingThrow
import com.datikaa.charlatan.core.domain.Skill
import com.datikaa.charlatan.modifier.ui.AddNewModifier
import com.datikaa.core.data.ModifierRepository
import kotlin.reflect.KClass

class CreateNewModifierUseCase(
    private val modifierRepository: ModifierRepository,
) {

    suspend operator fun invoke(newModifier: AddNewModifier.Modifier) {
        modifierRepository.insertModifier(
            newModifier.mapToDomain()
        )
    }
}

private fun AddNewModifier.Modifier.mapToDomain() = when (type) {
    AddNewModifier.Type.Proficiency -> Modifier.Proficiency(
        id = 0,
        name = name,
        description = "TODO",
        nestedModifiers = listOf(),
        proficiencyType = ability.convertProficiencyModifierToDomain()
    )

    AddNewModifier.Type.Score -> Modifier.Score(
        id = 7545,
        name = name,
        description = "TODO",
        nestedModifiers = listOf(),
        modifiableScoreType = ability.convertScoreModifierToDomain(),
        value = value ?: throw IllegalArgumentException("Value can't be null"),
    )
}

private fun AddNewModifier.Ability.convertProficiencyModifierToDomain(): KClass<out PossiblyProficient> =
    when (this) {
        AddNewModifier.Ability.SavingThrow.Strength -> SavingThrow.Strength::class
        AddNewModifier.Ability.SavingThrow.Dexterity -> SavingThrow.Dexterity::class
        AddNewModifier.Ability.SavingThrow.Constitution -> SavingThrow.Constitution::class
        AddNewModifier.Ability.SavingThrow.Intelligence -> SavingThrow.Intelligence::class
        AddNewModifier.Ability.SavingThrow.Wisdom -> SavingThrow.Wisdom::class
        AddNewModifier.Ability.SavingThrow.Charisma -> SavingThrow.Charisma::class
        AddNewModifier.Ability.Skill.Acrobatics -> Skill.Acrobatics::class
        AddNewModifier.Ability.Skill.AnimalHandling -> Skill.AnimalHandling::class
        AddNewModifier.Ability.Skill.Arcana -> Skill.Arcana::class
        AddNewModifier.Ability.Skill.Athletics -> Skill.Athletics::class
        AddNewModifier.Ability.Skill.Deception -> Skill.Deception::class
        AddNewModifier.Ability.Skill.History -> Skill.History::class
        AddNewModifier.Ability.Skill.Insight -> Skill.Insight::class
        AddNewModifier.Ability.Skill.Intimidation -> Skill.Intimidation::class
        AddNewModifier.Ability.Skill.Investigation -> Skill.Investigation::class
        AddNewModifier.Ability.Skill.Medicine -> Skill.Medicine::class
        AddNewModifier.Ability.Skill.Nature -> Skill.Nature::class
        AddNewModifier.Ability.Skill.Perception -> Skill.Perception::class
        AddNewModifier.Ability.Skill.Performance -> Skill.Performance::class
        AddNewModifier.Ability.Skill.Persuasion -> Skill.Persuasion::class
        AddNewModifier.Ability.Skill.Religion -> Skill.Religion::class
        AddNewModifier.Ability.Skill.SleightOfHand -> Skill.SleightOfHand::class
        AddNewModifier.Ability.Skill.Stealth -> Skill.Stealth::class
        AddNewModifier.Ability.Skill.Survival -> Skill.Survival::class
        AddNewModifier.Ability.BaseAbility.Strength,
        AddNewModifier.Ability.BaseAbility.Dexterity,
        AddNewModifier.Ability.BaseAbility.Constitution,
        AddNewModifier.Ability.BaseAbility.Intelligence,
        AddNewModifier.Ability.BaseAbility.Wisdom,
        AddNewModifier.Ability.BaseAbility.Charisma ->
            throw IllegalArgumentException("$this can't be converted to proficiency")
    }

private fun AddNewModifier.Ability.convertScoreModifierToDomain(): KClass<out ModifiableScore> =
    when (this) {
        AddNewModifier.Ability.SavingThrow.Strength -> SavingThrow.Strength::class
        AddNewModifier.Ability.SavingThrow.Dexterity -> SavingThrow.Dexterity::class
        AddNewModifier.Ability.SavingThrow.Constitution -> SavingThrow.Constitution::class
        AddNewModifier.Ability.SavingThrow.Intelligence -> SavingThrow.Intelligence::class
        AddNewModifier.Ability.SavingThrow.Wisdom -> SavingThrow.Wisdom::class
        AddNewModifier.Ability.SavingThrow.Charisma -> SavingThrow.Charisma::class
        AddNewModifier.Ability.Skill.Acrobatics -> Skill.Acrobatics::class
        AddNewModifier.Ability.Skill.AnimalHandling -> Skill.AnimalHandling::class
        AddNewModifier.Ability.Skill.Arcana -> Skill.Arcana::class
        AddNewModifier.Ability.Skill.Athletics -> Skill.Athletics::class
        AddNewModifier.Ability.Skill.Deception -> Skill.Deception::class
        AddNewModifier.Ability.Skill.History -> Skill.History::class
        AddNewModifier.Ability.Skill.Insight -> Skill.Insight::class
        AddNewModifier.Ability.Skill.Intimidation -> Skill.Intimidation::class
        AddNewModifier.Ability.Skill.Investigation -> Skill.Investigation::class
        AddNewModifier.Ability.Skill.Medicine -> Skill.Medicine::class
        AddNewModifier.Ability.Skill.Nature -> Skill.Nature::class
        AddNewModifier.Ability.Skill.Perception -> Skill.Perception::class
        AddNewModifier.Ability.Skill.Performance -> Skill.Performance::class
        AddNewModifier.Ability.Skill.Persuasion -> Skill.Persuasion::class
        AddNewModifier.Ability.Skill.Religion -> Skill.Religion::class
        AddNewModifier.Ability.Skill.SleightOfHand -> Skill.SleightOfHand::class
        AddNewModifier.Ability.Skill.Stealth -> Skill.Stealth::class
        AddNewModifier.Ability.Skill.Survival -> Skill.Survival::class
        AddNewModifier.Ability.BaseAbility.Strength -> Ability.Strength::class
        AddNewModifier.Ability.BaseAbility.Dexterity -> Ability.Dexterity::class
        AddNewModifier.Ability.BaseAbility.Constitution -> Ability.Constitution::class
        AddNewModifier.Ability.BaseAbility.Intelligence -> Ability.Intelligence::class
        AddNewModifier.Ability.BaseAbility.Wisdom -> Ability.Wisdom::class
        AddNewModifier.Ability.BaseAbility.Charisma -> Ability.Charisma::class
    }



