package com.datikaa.charlatan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.CharacterWithAttributes
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(characterEntity: CharacterEntity)

    @Update
    suspend fun update(characterEntity: CharacterEntity)

    @Query("DELETE FROM CharacterEntity")
    suspend fun deleteCharacter()

    @Query("SELECT * FROM CharacterEntity")
    fun flowCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM AttributeEntity WHERE characterId = :id")
    suspend fun getAttributesForCharacter(id: Int): List<AttributeEntity>

    @Transaction
    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun flowCharacterWithAttributes(id: Int): Flow<CharacterWithAttributes>

    @Transaction
    suspend fun insertOrUpdateCharacterWithAttributes(characterWithAttributes: CharacterWithAttributes) {
        insertOrUpdate(characterWithAttributes.characterEntity)
        insertOrUpdateAttributes(characterWithAttributes.attributes)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAttributes(characterAttributes: List<AttributeEntity>)
}