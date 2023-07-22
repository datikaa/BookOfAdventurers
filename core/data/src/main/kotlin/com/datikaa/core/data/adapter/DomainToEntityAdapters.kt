package com.datikaa.core.data.adapter

import com.datikaa.charlatan.core.database.entity.AbilityEntity
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.ModifierEntity
import com.datikaa.charlatan.core.database.partial.AbilityEntityPartialUpdate
import com.datikaa.charlatan.core.domain.Ability
import com.datikaa.charlatan.core.domain.Character
import com.datikaa.charlatan.core.domain.Modifier
import com.datikaa.charlatan.core.domain.SavingThrow
import com.datikaa.charlatan.core.domain.Skill
import kotlin.reflect.KClass

internal fun Ability.toEntity(id: Int, characterId: Int): AbilityEntity = AbilityEntity(
    id = id,
    characterId = characterId,
    type = toEntityEnum(),
    value = 0,
)

internal fun Ability.toEntityEnum(): AbilityEntity.Type = when (this) {
    is Ability.Charisma -> AbilityEntity.Type.Charisma
    is Ability.Constitution -> AbilityEntity.Type.Constitution
    is Ability.Dexterity -> AbilityEntity.Type.Dexterity
    is Ability.Intelligence -> AbilityEntity.Type.Intelligence
    is Ability.Strength -> AbilityEntity.Type.Strength
    is Ability.Wisdom -> AbilityEntity.Type.Wisdom
}

internal fun List<Ability>.mapToEntity(characterId: Int): List<AbilityEntity> = map { ability ->
    ability.toEntity(0, characterId)
}

internal fun Character.toEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    level = level
)

internal fun Ability.toPartialUpdate(id: Int) = AbilityEntityPartialUpdate(
    id = id,
    value = value,
)

internal fun Modifier.toEntity() = ModifierEntity(
    id = id,
    parentModifierId = null,
    name = name,
    description = description,
    type = toEntityEnum(),
    modifiableScoreType = toModifiableScoreTypeEntityEnum(),
    modifierValue = toModifierValue(),

)

internal fun Modifier.toEntityEnum() = when(this) {
    is Modifier.Holder -> ModifierEntity.Type.Holder
    is Modifier.Proficiency -> ModifierEntity.Type.Proficiency
    is Modifier.Score -> ModifierEntity.Type.Score
}

internal fun Modifier.toModifiableScoreTypeEntityEnum() = when(this) {
    is Modifier.Holder -> null
    is Modifier.Proficiency -> proficiencyType.toEntityEnum()
    is Modifier.Score -> modifiableScoreType.toEntityEnum()
}

internal fun Modifier.toModifierValue() = when(this) {
    is Modifier.Holder -> null
    is Modifier.Proficiency -> null
    is Modifier.Score -> value
}

internal fun KClass<*>.toEntityEnum() = when(this) {
    Ability.Strength::class -> ModifierEntity.ModifiableScoreType.BaseAbility_Strength
    Ability.Dexterity::class -> ModifierEntity.ModifiableScoreType.BaseAbility_Dexterity
    Ability.Constitution::class -> ModifierEntity.ModifiableScoreType.BaseAbility_Constitution
    Ability.Intelligence::class -> ModifierEntity.ModifiableScoreType.BaseAbility_Intelligence
    Ability.Wisdom::class -> ModifierEntity.ModifiableScoreType.BaseAbility_Wisdom
    Ability.Charisma::class -> ModifierEntity.ModifiableScoreType.BaseAbility_Charisma

    Skill.Acrobatics::class -> ModifierEntity.ModifiableScoreType.Skill_Acrobatics
    Skill.AnimalHandling::class -> ModifierEntity.ModifiableScoreType.Skill_AnimalHandling
    Skill.Arcana::class -> ModifierEntity.ModifiableScoreType.Skill_Arcana
    Skill.Athletics::class -> ModifierEntity.ModifiableScoreType.Skill_Athletics
    Skill.Deception::class -> ModifierEntity.ModifiableScoreType.Skill_Deception
    Skill.History::class -> ModifierEntity.ModifiableScoreType.Skill_History
    Skill.Insight::class -> ModifierEntity.ModifiableScoreType.Skill_Insight
    Skill.Intimidation::class -> ModifierEntity.ModifiableScoreType.Skill_Intimidation
    Skill.Investigation::class -> ModifierEntity.ModifiableScoreType.Skill_Investigation
    Skill.Medicine::class -> ModifierEntity.ModifiableScoreType.Skill_Medicine
    Skill.Nature::class -> ModifierEntity.ModifiableScoreType.Skill_Nature
    Skill.Perception::class -> ModifierEntity.ModifiableScoreType.Skill_Perception
    Skill.Performance::class -> ModifierEntity.ModifiableScoreType.Skill_Performance
    Skill.Persuasion::class -> ModifierEntity.ModifiableScoreType.Skill_Persuasion
    Skill.Religion::class -> ModifierEntity.ModifiableScoreType.Skill_Religion
    Skill.SleightOfHand::class -> ModifierEntity.ModifiableScoreType.Skill_SleightOfHand
    Skill.Stealth::class -> ModifierEntity.ModifiableScoreType.Skill_Stealth
    Skill.Survival::class -> ModifierEntity.ModifiableScoreType.Skill_Survival

    SavingThrow.Strength::class -> ModifierEntity.ModifiableScoreType.SavingThrow_Strength
    SavingThrow.Dexterity::class -> ModifierEntity.ModifiableScoreType.SavingThrow_Dexterity
    SavingThrow.Constitution::class -> ModifierEntity.ModifiableScoreType.SavingThrow_Constitution
    SavingThrow.Intelligence::class -> ModifierEntity.ModifiableScoreType.SavingThrow_Intelligence
    SavingThrow.Wisdom::class -> ModifierEntity.ModifiableScoreType.SavingThrow_Wisdom
    SavingThrow.Charisma::class -> ModifierEntity.ModifiableScoreType.SavingThrow_Charisma

    else -> throw IllegalStateException("Unknown type: $this")
}
