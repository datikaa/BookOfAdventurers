package com.datikaa.charlatan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.datikaa.charlatan.core.database.entity.CharacterEntity
import com.datikaa.charlatan.core.database.entity.CharacterWithAttributes
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(characterEntity: CharacterEntity)

    @Update
    suspend fun update(characterEntity: CharacterEntity)

    @Query("DELETE FROM CharacterEntity")
    suspend fun deleteCharacter()

    @Query("SELECT * FROM CharacterEntity")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Transaction
    @Query("SELECT * FROM CharacterEntity WHERE id = :id")
    fun getCharacterWithAttributes(id: Int): Flow<CharacterWithAttributes>
}