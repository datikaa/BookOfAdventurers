package com.datikaa.bookofadventurers.core.data.adapter

import com.datikaa.bookofadventurers.core.database.realm.ModifiableScoreType
import com.datikaa.bookofadventurers.core.database.realm.RealmProficiencyModifier
import com.datikaa.bookofadventurers.core.database.realm.RealmScoreModifier
import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.ModifiableScore
import com.datikaa.bookofadventurers.core.domain.Modifier
import com.datikaa.bookofadventurers.core.domain.PossiblyProficient
import com.datikaa.bookofadventurers.core.domain.SavingThrow
import com.datikaa.bookofadventurers.core.domain.Skill
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
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
    Ability.Strength::class -> ModifiableScoreType.BaseAbility_Strength
    Ability.Dexterity::class -> ModifiableScoreType.BaseAbility_Dexterity
    Ability.Constitution::class -> ModifiableScoreType.BaseAbility_Constitution
    Ability.Intelligence::class -> ModifiableScoreType.BaseAbility_Intelligence
    Ability.Wisdom::class -> ModifiableScoreType.BaseAbility_Wisdom
    Ability.Charisma::class -> ModifiableScoreType.BaseAbility_Charisma

    Skill.Acrobatics::class -> ModifiableScoreType.Skill_Acrobatics
    Skill.AnimalHandling::class -> ModifiableScoreType.Skill_AnimalHandling
    Skill.Arcana::class -> ModifiableScoreType.Skill_Arcana
    Skill.Athletics::class -> ModifiableScoreType.Skill_Athletics
    Skill.Deception::class -> ModifiableScoreType.Skill_Deception
    Skill.History::class -> ModifiableScoreType.Skill_History
    Skill.Insight::class -> ModifiableScoreType.Skill_Insight
    Skill.Intimidation::class -> ModifiableScoreType.Skill_Intimidation
    Skill.Investigation::class -> ModifiableScoreType.Skill_Investigation
    Skill.Medicine::class -> ModifiableScoreType.Skill_Medicine
    Skill.Nature::class -> ModifiableScoreType.Skill_Nature
    Skill.Perception::class -> ModifiableScoreType.Skill_Perception
    Skill.Performance::class -> ModifiableScoreType.Skill_Performance
    Skill.Persuasion::class -> ModifiableScoreType.Skill_Persuasion
    Skill.Religion::class -> ModifiableScoreType.Skill_Religion
    Skill.SleightOfHand::class -> ModifiableScoreType.Skill_SleightOfHand
    Skill.Stealth::class -> ModifiableScoreType.Skill_Stealth
    Skill.Survival::class -> ModifiableScoreType.Skill_Survival

    SavingThrow.Strength::class -> ModifiableScoreType.SavingThrow_Strength
    SavingThrow.Dexterity::class -> ModifiableScoreType.SavingThrow_Dexterity
    SavingThrow.Constitution::class -> ModifiableScoreType.SavingThrow_Constitution
    SavingThrow.Intelligence::class -> ModifiableScoreType.SavingThrow_Intelligence
    SavingThrow.Wisdom::class -> ModifiableScoreType.SavingThrow_Wisdom
    SavingThrow.Charisma::class -> ModifiableScoreType.SavingThrow_Charisma

    else -> throw IllegalStateException("Unknown type: $this")
}

@JvmName("mapRealmScoreModifierToDomain")
internal fun RealmList<RealmScoreModifier>.toDomain() = map {
    it.toDomain()
}

internal fun RealmScoreModifier.toDomain() = Modifier.Score(
    id = _id,
    name = name,
    description = description,
    nestedModifiers = listOf(),
    modifiableScoreType = ModifiableScoreType.entries[modifiableScoreType].toDomainAsModifiableScoreType(),
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
    proficiencyType = ModifiableScoreType.entries[proficiencyType].toDomainAsPossiblyProficient(),
)

private fun ModifiableScoreType?.toDomainAsPossiblyProficient(): KClass<out PossiblyProficient> =
    when (this) {
        ModifiableScoreType.SavingThrow_Strength -> SavingThrow.Strength::class
        ModifiableScoreType.SavingThrow_Dexterity -> SavingThrow.Dexterity::class
        ModifiableScoreType.SavingThrow_Constitution -> SavingThrow.Constitution::class
        ModifiableScoreType.SavingThrow_Intelligence -> SavingThrow.Intelligence::class
        ModifiableScoreType.SavingThrow_Wisdom -> SavingThrow.Wisdom::class
        ModifiableScoreType.SavingThrow_Charisma -> SavingThrow.Charisma::class

        ModifiableScoreType.Skill_Acrobatics -> Skill.Acrobatics::class
        ModifiableScoreType.Skill_AnimalHandling -> Skill.AnimalHandling::class
        ModifiableScoreType.Skill_Arcana -> Skill.Arcana::class
        ModifiableScoreType.Skill_Athletics -> Skill.Athletics::class
        ModifiableScoreType.Skill_Deception -> Skill.Deception::class
        ModifiableScoreType.Skill_History -> Skill.History::class
        ModifiableScoreType.Skill_Insight -> Skill.Insight::class
        ModifiableScoreType.Skill_Intimidation -> Skill.Intimidation::class
        ModifiableScoreType.Skill_Investigation -> Skill.Investigation::class
        ModifiableScoreType.Skill_Medicine -> Skill.Medicine::class
        ModifiableScoreType.Skill_Nature -> Skill.Nature::class
        ModifiableScoreType.Skill_Perception -> Skill.Perception::class
        ModifiableScoreType.Skill_Performance -> Skill.Performance::class
        ModifiableScoreType.Skill_Persuasion -> Skill.Persuasion::class
        ModifiableScoreType.Skill_Religion -> Skill.Religion::class
        ModifiableScoreType.Skill_SleightOfHand -> Skill.SleightOfHand::class
        ModifiableScoreType.Skill_Stealth -> Skill.Stealth::class
        ModifiableScoreType.Skill_Survival -> Skill.Survival::class

        ModifiableScoreType.BaseAbility_Strength,
        ModifiableScoreType.BaseAbility_Dexterity,
        ModifiableScoreType.BaseAbility_Constitution,
        ModifiableScoreType.BaseAbility_Intelligence,
        ModifiableScoreType.BaseAbility_Wisdom,
        ModifiableScoreType.BaseAbility_Charisma ->
            throw IllegalStateException("BaseAbility can't be PossiblyProficient")

        null -> throw IllegalStateException("ModifiableScoreType can't be null for Modifier.Proficiency")
    }

private fun ModifiableScoreType?.toDomainAsModifiableScoreType(): KClass<out ModifiableScore> =
    when (this) {
        ModifiableScoreType.SavingThrow_Strength -> SavingThrow.Strength::class
        ModifiableScoreType.SavingThrow_Dexterity -> SavingThrow.Dexterity::class
        ModifiableScoreType.SavingThrow_Constitution -> SavingThrow.Constitution::class
        ModifiableScoreType.SavingThrow_Intelligence -> SavingThrow.Intelligence::class
        ModifiableScoreType.SavingThrow_Wisdom -> SavingThrow.Wisdom::class
        ModifiableScoreType.SavingThrow_Charisma -> SavingThrow.Charisma::class

        ModifiableScoreType.Skill_Acrobatics -> Skill.Acrobatics::class
        ModifiableScoreType.Skill_AnimalHandling -> Skill.AnimalHandling::class
        ModifiableScoreType.Skill_Arcana -> Skill.Arcana::class
        ModifiableScoreType.Skill_Athletics -> Skill.Athletics::class
        ModifiableScoreType.Skill_Deception -> Skill.Deception::class
        ModifiableScoreType.Skill_History -> Skill.History::class
        ModifiableScoreType.Skill_Insight -> Skill.Insight::class
        ModifiableScoreType.Skill_Intimidation -> Skill.Intimidation::class
        ModifiableScoreType.Skill_Investigation -> Skill.Investigation::class
        ModifiableScoreType.Skill_Medicine -> Skill.Medicine::class
        ModifiableScoreType.Skill_Nature -> Skill.Nature::class
        ModifiableScoreType.Skill_Perception -> Skill.Perception::class
        ModifiableScoreType.Skill_Performance -> Skill.Performance::class
        ModifiableScoreType.Skill_Persuasion -> Skill.Persuasion::class
        ModifiableScoreType.Skill_Religion -> Skill.Religion::class
        ModifiableScoreType.Skill_SleightOfHand -> Skill.SleightOfHand::class
        ModifiableScoreType.Skill_Stealth -> Skill.Stealth::class
        ModifiableScoreType.Skill_Survival -> Skill.Survival::class

        ModifiableScoreType.BaseAbility_Strength -> Ability.Strength::class
        ModifiableScoreType.BaseAbility_Dexterity -> Ability.Dexterity::class
        ModifiableScoreType.BaseAbility_Constitution -> Ability.Constitution::class
        ModifiableScoreType.BaseAbility_Intelligence -> Ability.Intelligence::class
        ModifiableScoreType.BaseAbility_Wisdom -> Ability.Wisdom::class
        ModifiableScoreType.BaseAbility_Charisma -> Ability.Charisma::class

        null -> throw IllegalStateException("BaseAbility can't be null for Modifier.Score")
    }

