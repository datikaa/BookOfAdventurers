package com.datikaa.bookofadventurers.core.data.adapter.modifier

import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Modifier
import com.datikaa.bookofadventurers.core.domain.SavingThrow
import com.datikaa.bookofadventurers.core.domain.Skill
import kotlin.reflect.KClass

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
