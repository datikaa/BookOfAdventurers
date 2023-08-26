package com.datikaa.bookofadventurers.core.data.adapter.modifier

import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity
import com.datikaa.bookofadventurers.core.database.realm.RealmProficiencyModifier
import com.datikaa.bookofadventurers.core.database.realm.RealmScoreModifier
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.ModifiableScore
import com.datikaa.bookofadventurers.core.domain.Modifier
import com.datikaa.bookofadventurers.core.domain.PossiblyProficient
import com.datikaa.bookofadventurers.core.domain.SavingThrow
import com.datikaa.bookofadventurers.core.domain.Skill
import io.realm.kotlin.types.RealmList
import kotlin.reflect.KClass

@JvmName("mapRealmScoreModifierToDomain")
internal fun RealmList<RealmScoreModifier>.toDomain() = map {
    it.toDomain()
}

internal fun RealmScoreModifier.toDomain() = Modifier.Score(
    id = _id,
    name = name,
    description = description,
    nestedModifiers = listOf(),
    modifiableScoreType = ModifierEntity.ModifiableScoreType.entries[modifiableScoreType].toDomainAsModifiableScoreType(),
    value = modifierValue
)

@JvmName("mapRealmProficiencyModifierToDomain")
internal fun RealmList<RealmProficiencyModifier>.toDomain() = map {
    it.toDomain()
}

internal fun RealmProficiencyModifier.toDomain() = Modifier.Proficiency(
    id = _id,
    name = name,
    description = description,
    nestedModifiers = listOf(),
    proficiencyType = ModifierEntity.ModifiableScoreType.entries[proficiencyType].toDomainAsPossiblyProficient(),
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

