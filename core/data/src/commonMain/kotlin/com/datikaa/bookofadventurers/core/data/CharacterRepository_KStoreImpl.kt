package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.domain.Background
import com.datikaa.bookofadventurers.core.domain.BoaCharacter
import com.datikaa.bookofadventurers.core.domain.CharacterClass
import com.datikaa.bookofadventurers.core.store.CharacterStore
import com.datikaa.bookofadventurers.core.store.models.BackgroundEntity
import com.datikaa.bookofadventurers.core.store.models.CharacterEntity
import com.datikaa.bookofadventurers.core.store.models.ClassEntity
import io.github.xxfast.kstore.extensions.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class CharacterRepository_KStoreImpl(
    characterStore: CharacterStore,
) : CharacterRepository {

    private val characterStore = characterStore.store

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
                    backgroundEntity = characterEntity.backgroundEntity.copy(
                        id = character.characterBackground.id,
                        name = character.characterBackground.name,
                        featureTitle = character.characterBackground.featureTitle,
                        featureDescription = character.characterBackground.featureDescription,
                        suggestedCharacteristics = character.characterBackground.suggestedCharacteristics,
                    ),
                    classEntity = characterEntity.classEntity.copy(
                        id = character.characterClass.id,
                        name = character.characterClass.name,
                    ),
                )
            } else characterEntity
        }


    override suspend fun insertCharacter(character: BoaCharacter): Long {
        characterStore.update { list ->
            val mutableList = list.orEmpty().toMutableList()
            mutableList.apply {
                val entity = CharacterEntity(
                    id = mutableList.size,
                    name = character.name,
                    level = character.level,
                    backgroundEntity = BackgroundEntity(
                        id = character.characterBackground.id,
                        name = character.characterBackground.name,
                        featureTitle = character.characterBackground.featureTitle,
                        featureDescription = character.characterBackground.featureDescription,
                        suggestedCharacteristics = character.characterBackground.suggestedCharacteristics,
                    ),
                    classEntity = ClassEntity(
                        id = character.characterClass.id,
                        name = character.characterClass.name,
                    ),
                )
                add(entity)
            }
        }
        return characterStore.get()!!.lastIndex.toLong()
    }

    override suspend fun linkCharacterWithSelectedModifiers(charId: Long, modifierIds: List<Long>) {

    }

    override suspend fun clearAll() {
    }
}

private fun CharacterEntity.toDomain() = BoaCharacter(
    id = id,
    name = name,
    level = level,
    characterBackground = Background(
        id = backgroundEntity.id,
        name = backgroundEntity.name,
        featureTitle = backgroundEntity.featureTitle,
        featureDescription = backgroundEntity.featureDescription,
        suggestedCharacteristics = backgroundEntity.suggestedCharacteristics,
        skillProficiencies = emptyList(),
    ),
    characterClass = CharacterClass(
        id = classEntity.id,
        name = classEntity.name,
        savingThrowProficiencies = emptyList(),
        skillProficiencies = emptyList()
    ),
    abilityList = emptyList(),
    modifiers = emptyList()
)
