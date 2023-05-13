package com.datikaa.charlatan.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: Character)

    @Update
    fun update(character: Character)

    @Query("DELETE FROM Character")
    fun deleteCharacter()

    @Query("SELECT * FROM Character")
    fun getCharacters(): Flow<List<Character>>
}