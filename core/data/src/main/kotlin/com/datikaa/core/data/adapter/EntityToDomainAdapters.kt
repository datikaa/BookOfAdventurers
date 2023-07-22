package com.datikaa.core.data.adapter

import com.datikaa.charlatan.core.database.entity.AbilityEntity
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.CharacterWithAbilitiesAndModifiers
import com.datikaa.charlatan.core.database.entity.ModifierEntity
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.core.domain.ModifiableScore
import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.charlatan.core.domain.PossiblyProficient
import com.datikaa.charlatan.core.domain.SavingThrow
import com.datikaa.charlatan.core.domain.Skill
import kotlin.reflect.KClass

internal fun AbilityEntity.toDomain(): Ability = when (type) {
    AbilityEntity.Type.Strength -> Ability.Strength(value)
    AbilityEntity.Type.Dexterity -> Ability.Dexterity(value)
    AbilityEntity.Type.Constitution -> Ability.Constitution(value)
    AbilityEntity.Type.Intelligence -> Ability.Intelligence(value)
    AbilityEntity.Type.Wisdom -> Ability.Wisdom(value)
    AbilityEntity.Type.Charisma -> Ability.Charisma(value)
}

@JvmName("mapAbilityEntityToDomain")
internal fun List<AbilityEntity>.mapToDomain() = map { it.toDomain() }

internal fun CharacterWithAbilitiesAndModifiers.toDomain(): Character = Character(
    id = characterEntity.id,
    name = characterEntity.name,
    level = characterEntity.level,
    abilityList = abilityEntities.mapToDomain(),
    modifiers = modifierEntities.mapToDomain(),
)

@JvmName("mapCharacterEntityToDomain")
internal fun List<CharacterEntity>.mapToDomain() = map { it.toDomain() }
internal fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    level = level,
    abilityList = emptyList(),
    modifiers = emptyList(),
)

internal fun List<ModifierEntity>.mapToDomain() =  map { it.toDomain() }
internal fun ModifierEntity.toDomain() = when (type) {
    ModifierEntity.Type.Holder -> toDomainAsHolder()
    ModifierEntity.Type.Score -> toDomainAsScore()
    ModifierEntity.Type.Proficiency -> toDomainAsProficiency()
}

private fun ModifierEntity.toDomainAsHolder() = Modifier.Holder(
    id = id,
    name = name,
    description = description,
    nestedModifiers = listOf(),
)

private fun ModifierEntity.toDomainAsScore() = Modifier.Score(
    id = id,
    name = name,
    description = description,
    nestedModifiers = listOf(),
    modifiableScoreType = modifiableScoreType.toDomainAsModifiableScoreType(),
    value = modifierValue ?: throw IllegalStateException("Illegal state, modifierValue was null"),
)

private fun ModifierEntity.toDomainAsProficiency() = Modifier.Proficiency(
    id = id,
    name = name,
    description = description,
    nestedModifiers = listOf(),
    proficiencyType = modifiableScoreType.toDomainAsPossiblyProficient(),
)

private fun ModifierEntity.ModifiableScoreType?.toDomainAsPossiblyProficient(): KClass<out PossiblyProficient> =
    when (this) {
        ModifierEntity.ModifiableScoreType.SavingThrow_Strength -> SavingThrow.Strength::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Dexterity -> SavingThrow.Dexterity::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Constitution -> SavingThrow.Constitution::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Intelligence -> SavingThrow.Intelligence::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Wisdom -> SavingThrow.Wisdom::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Charisma -> SavingThrow.Charisma::class

        ModifierEntity.ModifiableScoreType.Skill_Acrobatics -> Skill.Acrobatics::class
        ModifierEntity.ModifiableScoreType.Skill_AnimalHandling -> Skill.AnimalHandling::class
        ModifierEntity.ModifiableScoreType.Skill_Arcana -> Skill.Arcana::class
        ModifierEntity.ModifiableScoreType.Skill_Athletics -> Skill.Athletics::class
        ModifierEntity.ModifiableScoreType.Skill_Deception -> Skill.Deception::class
        ModifierEntity.ModifiableScoreType.Skill_History -> Skill.History::class
        ModifierEntity.ModifiableScoreType.Skill_Insight -> Skill.Insight::class
        ModifierEntity.ModifiableScoreType.Skill_Intimidation -> Skill.Intimidation::class
        ModifierEntity.ModifiableScoreType.Skill_Investigation -> Skill.Investigation::class
        ModifierEntity.ModifiableScoreType.Skill_Medicine -> Skill.Medicine::class
        ModifierEntity.ModifiableScoreType.Skill_Nature -> Skill.Nature::class
        ModifierEntity.ModifiableScoreType.Skill_Perception -> Skill.Perception::class
        ModifierEntity.ModifiableScoreType.Skill_Performance -> Skill.Performance::class
        ModifierEntity.ModifiableScoreType.Skill_Persuasion -> Skill.Persuasion::class
        ModifierEntity.ModifiableScoreType.Skill_Religion -> Skill.Religion::class
        ModifierEntity.ModifiableScoreType.Skill_SleightOfHand -> Skill.SleightOfHand::class
        ModifierEntity.ModifiableScoreType.Skill_Stealth -> Skill.Stealth::class
        ModifierEntity.ModifiableScoreType.Skill_Survival -> Skill.Survival::class

        ModifierEntity.ModifiableScoreType.BaseAbility_Strength,
        ModifierEntity.ModifiableScoreType.BaseAbility_Dexterity,
        ModifierEntity.ModifiableScoreType.BaseAbility_Constitution,
        ModifierEntity.ModifiableScoreType.BaseAbility_Intelligence,
        ModifierEntity.ModifiableScoreType.BaseAbility_Wisdom,
        ModifierEntity.ModifiableScoreType.BaseAbility_Charisma ->
            throw IllegalStateException("BaseAbility can't be PossiblyProficient")

        null -> throw IllegalStateException("ModifierEntity.ModifiableScoreType can't be null for Modifier.Proficiency")
    }

private fun ModifierEntity.ModifiableScoreType?.toDomainAsModifiableScoreType(): KClass<out ModifiableScore> =
    when (this) {
        ModifierEntity.ModifiableScoreType.SavingThrow_Strength -> SavingThrow.Strength::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Dexterity -> SavingThrow.Dexterity::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Constitution -> SavingThrow.Constitution::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Intelligence -> SavingThrow.Intelligence::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Wisdom -> SavingThrow.Wisdom::class
        ModifierEntity.ModifiableScoreType.SavingThrow_Charisma -> SavingThrow.Charisma::class

        ModifierEntity.ModifiableScoreType.Skill_Acrobatics -> Skill.Acrobatics::class
        ModifierEntity.ModifiableScoreType.Skill_AnimalHandling -> Skill.AnimalHandling::class
        ModifierEntity.ModifiableScoreType.Skill_Arcana -> Skill.Arcana::class
        ModifierEntity.ModifiableScoreType.Skill_Athletics -> Skill.Athletics::class
        ModifierEntity.ModifiableScoreType.Skill_Deception -> Skill.Deception::class
        ModifierEntity.ModifiableScoreType.Skill_History -> Skill.History::class
        ModifierEntity.ModifiableScoreType.Skill_Insight -> Skill.Insight::class
        ModifierEntity.ModifiableScoreType.Skill_Intimidation -> Skill.Intimidation::class
        ModifierEntity.ModifiableScoreType.Skill_Investigation -> Skill.Investigation::class
        ModifierEntity.ModifiableScoreType.Skill_Medicine -> Skill.Medicine::class
        ModifierEntity.ModifiableScoreType.Skill_Nature -> Skill.Nature::class
        ModifierEntity.ModifiableScoreType.Skill_Perception -> Skill.Perception::class
        ModifierEntity.ModifiableScoreType.Skill_Performance -> Skill.Performance::class
        ModifierEntity.ModifiableScoreType.Skill_Persuasion -> Skill.Persuasion::class
        ModifierEntity.ModifiableScoreType.Skill_Religion -> Skill.Religion::class
        ModifierEntity.ModifiableScoreType.Skill_SleightOfHand -> Skill.SleightOfHand::class
        ModifierEntity.ModifiableScoreType.Skill_Stealth -> Skill.Stealth::class
        ModifierEntity.ModifiableScoreType.Skill_Survival -> Skill.Survival::class

        ModifierEntity.ModifiableScoreType.BaseAbility_Strength -> Ability.Strength::class
        ModifierEntity.ModifiableScoreType.BaseAbility_Dexterity -> Ability.Dexterity::class
        ModifierEntity.ModifiableScoreType.BaseAbility_Constitution -> Ability.Constitution::class
        ModifierEntity.ModifiableScoreType.BaseAbility_Intelligence -> Ability.Intelligence::class
        ModifierEntity.ModifiableScoreType.BaseAbility_Wisdom -> Ability.Wisdom::class
        ModifierEntity.ModifiableScoreType.BaseAbility_Charisma -> Ability.Charisma::class

        null -> throw IllegalStateException("BaseAbility can't be null for Modifier.Score")
    }

