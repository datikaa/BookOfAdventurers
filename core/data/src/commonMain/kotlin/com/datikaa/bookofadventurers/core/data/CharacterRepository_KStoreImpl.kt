package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.domain.Ability
import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass
import com.datikaa.bookofadventurers.core.store.BackgroundStore
import com.datikaa.bookofadventurers.core.store.CharacterStore
import com.datikaa.bookofadventurers.core.store.ClassStore
import com.datikaa.bookofadventurers.core.store.ModifierStore
import com.datikaa.bookofadventurers.core.store.models.AbilityEntity
import com.datikaa.bookofadventurers.core.store.models.CharacterEntity
import io.github.xxfast.kstore.extensions.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class CharacterRepository_KStoreImpl(
    backgroundStore: BackgroundStore,
    characterStore: CharacterStore,
    classStore: ClassStore,
    modifierStore: ModifierStore,
) : CharacterRepository {

    private val characterStore = characterStore.store
    private val backgroundStore = backgroundStore.store
    private val classStore = classStore.store
    private val modifierStore = modifierStore.store

    override fun flowListOfCharacters(): Flow<List<BoaCharacter>> = characterStore.updates
        .filterNotNull()
        .map { characterEntities ->
            characterEntities.map { characterEntity -> characterEntity.toDomain() }
        }

    override fun flowCharacter(id: Int): Flow<BoaCharacter> = characterStore.updates
        .filterNotNull()
        .map { characterEntities -> characterEntities.first { it.id == id } }
        .map { it.toDomain() }


    override suspend fun updateCharacter(character: BoaCharacter) =
        characterStore.map { characterEntity ->
            if (characterEntity.id == character.id) {
                characterEntity.copy(
                    name = character.name,
                    level = character.level,
//                    backgroundEntity = characterEntity.backgroundEntity.copy(
//                        name = character.characterBackground.name,
//                        featureTitle = character.characterBackground.featureTitle,
//                        featureDescription = character.characterBackground.featureDescription,
//                        suggestedCharacteristics = character.characterBackground.suggestedCharacteristics,
//                    ),
//                    classEntity = characterEntity.classEntity.copy(
//                        id = character.characterClass.id,
//                        name = character.characterClass.name,
//                    ),
                )
            } else characterEntity
        }


    override suspend fun insertCharacter(character: BoaCharacter): Long {
        val background = backgroundStore.get()!![character.characterBackground.id.toInt() - 1]
        val clazz = classStore.get()!![character.characterClass.id.toInt()]
        characterStore.update { list ->
            val mutableList = list.orEmpty().toMutableList()
            mutableList.apply {
                val entity = CharacterEntity(
                    id = mutableList.size,
                    name = character.name,
                    level = character.level,
                    backgroundEntity = background,
                    classEntity = clazz,
                    modifiers = emptyList(),
                    abilities = character.abilityList.map { ability ->
                        when (ability) {
                            is Ability.Charisma -> AbilityEntity(
                                type = AbilityEntity.Type.Charisma,
                                value = ability.value,
                            )

                            is Ability.Constitution -> AbilityEntity(
                                type = AbilityEntity.Type.Constitution,
                                value = ability.value,
                            )

                            is Ability.Dexterity -> AbilityEntity(
                                type = AbilityEntity.Type.Dexterity,
                                value = ability.value,
                            )

                            is Ability.Intelligence -> AbilityEntity(
                                type = AbilityEntity.Type.Intelligence,
                                value = ability.value,
                            )

                            is Ability.Strength -> AbilityEntity(
                                type = AbilityEntity.Type.Strength,
                                value = ability.value,
                            )

                            is Ability.Wisdom -> AbilityEntity(
                                type = AbilityEntity.Type.Wisdom,
                                value = ability.value,
                            )
                        }

                    },
                )
                add(entity)
            }
        }
        return characterStore.get()!!.lastIndex.toLong()
    }

    override suspend fun linkCharacterWithSelectedModifiers(charId: Long, modifierIds: List<Long>) {
        val modifiers = modifierStore.get()!!.filter { modifierEntity ->
            modifierIds.contains(modifierEntity.id.toLong())
        }
        characterStore.map { characterEntity ->
            if (characterEntity.id == charId.toInt()) {
                characterEntity.copy(
                    modifiers = modifiers
                )
            } else characterEntity
        }
    }

    override suspend fun clearAll() {
    }
}

private fun CharacterEntity.toDomain() = BoaCharacter(
    id = id,
    name = name,
    level = level,
    characterBackground = Background(
        id = 0,
        name = backgroundEntity.name,
        featureTitle = backgroundEntity.featureTitle,
        featureDescription = backgroundEntity.featureDescription,
        suggestedCharacteristics = backgroundEntity.suggestedCharacteristics,
        skillProficiencies = emptyList(),
    ),
    characterClass = CharacterClass(
        id = 0,
        name = classEntity.name,
        savingThrowProficiencies = emptyList(),
        skillProficiencies = emptyList()
    ),
    abilityList = abilities.map { ability ->
        when (ability.type) {
            AbilityEntity.Type.Strength -> Ability.Strength(ability.value)
            AbilityEntity.Type.Dexterity -> Ability.Dexterity(ability.value)
            AbilityEntity.Type.Constitution -> Ability.Constitution(ability.value)
            AbilityEntity.Type.Intelligence -> Ability.Intelligence(ability.value)
            AbilityEntity.Type.Wisdom -> Ability.Wisdom(ability.value)
            AbilityEntity.Type.Charisma -> Ability.Charisma(ability.value)
        }
    },
    modifiers = emptyList()
)
