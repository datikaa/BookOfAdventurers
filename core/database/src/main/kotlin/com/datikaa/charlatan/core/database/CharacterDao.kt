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

    @Update
    fun updateAttribute(characterAttribute: CharAttribute)

    @Query("SELECT * FROM CharAttribute")
    fun getAttributes(): Flow<List<CharAttribute>>

    @Insert
    fun insertAttribute(characterAttribute: CharAttribute)

    @Query("DELETE FROM CharAttribute")
    fun deleteAttributes()

    @Query("DELETE FROM Character")
    fun deleteCharacter()
}