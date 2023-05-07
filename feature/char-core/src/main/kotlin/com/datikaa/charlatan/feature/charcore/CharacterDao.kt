package com.datikaa.charlatan.feature.charcore

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface CharacterDao {

    @Insert
    fun insert(character: Character)

    @Update
    fun update(character: Character)

    @Update
    fun updateAttribute(characterAttribute: CharAttribute)

    @Transaction
    @Query("SELECT * FROM Character")
    fun getCharactersWithAttributes(): List<CharacterWithAttributes>
}