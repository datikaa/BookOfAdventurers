package com.datikaa.bookofadventurers.core.data.adapter.modifier

import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity
import com.datikaa.bookofadventurers.core.database.realm.RealmProficiencyModifier
import com.datikaa.bookofadventurers.core.database.realm.RealmScoreModifier
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Modifier
import com.datikaa.bookofadventurers.core.domain.SavingThrow
import com.datikaa.bookofadventurers.core.domain.Skill
import io.realm.kotlin.ext.toRealmList
import kotlin.reflect.KClass

internal fun List<Modifier>.toProficiencyRealm() = filterIsInstance<Modifier.Proficiency>()
    .map { it.toRealm() }
    .toRealmList()

internal fun List<Modifier>.toScoreRealm() = filterIsInstance<Modifier.Score>()
    .map { it.toRealm() }
    .toRealmList()

internal fun Modifier.Proficiency.toRealm() = RealmProficiencyModifier().apply {
    name = this@toRealm.name
    description = this@toRealm.description
    proficiencyType = this@toRealm.proficiencyType.toEntityEnum().ordinal
}

internal fun Modifier.Score.toRealm() = RealmScoreModifier().apply {
    name = this@toRealm.name
    description = this@toRealm.description
    modifiableScoreType = this@toRealm.modifiableScoreType.toEntityEnum().ordinal
    modifierValue = this@toRealm.value
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
