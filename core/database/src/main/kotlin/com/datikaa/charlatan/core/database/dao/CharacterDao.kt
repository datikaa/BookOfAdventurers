package com.datikaa.charlatan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.CharacterWithAttributesAndModifiers
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterEntity: CharacterEntity): Long

    @Update
    suspend fun updateCharacter(characterEntity: CharacterEntity)

    @Query("DELETE FROM CharacterEntity")
    suspend fun deleteCharacters()

    @Query("SELECT * FROM CharacterEntity")
    fun flowCharacters(): Flow<List<CharacterEntity>>

    @Transaction
    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun flowCharacterWithAttributes(id: Int): Flow<CharacterWithAttributesAndModifiers>

    @Transaction
    suspend fun insertOrUpdateCharacterWithAttributes(characterWithAttributesAndModifiers: CharacterWithAttributesAndModifiers) {
        insertCharacter(characterWithAttributesAndModifiers.characterEntity)
        insertOrUpdateAttributes(characterWithAttributesAndModifiers.attributes)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAttributes(characterAttributes: List<AttributeEntity>)
}
