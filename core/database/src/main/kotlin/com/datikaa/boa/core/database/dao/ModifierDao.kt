package com.datikaa.boa.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.datikaa.boa.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.boa.core.database.entity.ModifierEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ModifierDao {

    @Insert
    suspend fun insertModifier(modifierEntity: ModifierEntity): Long
    @Insert
    suspend fun insertCharacterModifierCrossRef(characterModifierCrossRef: CharacterModifierCrossRef): Long

    @Query("DELETE FROM ModifierEntity")
    suspend fun deleteAllModifiers()

    @Query("SELECT * FROM ModifierEntity")
    fun flowModifiers(): Flow<List<ModifierEntity>>

    @Query("SELECT * FROM ModifierEntity WHERE id = :id")
    suspend fun getModifier(id: Int): ModifierEntity

    @Query("SELECT * FROM CharacterModifierCrossRef WHERE characterId = :characterId AND modifierId = :modifierId LIMIT 1")
    suspend fun getCharacterModifierCrossRef(modifierId: Long, characterId: Long): CharacterModifierCrossRef?

    @Query("DELETE FROM CharacterModifierCrossRef WHERE characterId = :characterId AND modifierId = :modifierId")
    suspend fun removeCharacterModifierCrossRef(modifierId: Long, characterId: Long)
}
