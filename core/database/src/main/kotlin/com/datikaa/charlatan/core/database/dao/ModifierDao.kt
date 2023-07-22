package com.datikaa.charlatan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.datikaa.charlatan.core.database.crossref.CharacterModifierCrossRef
import com.datikaa.charlatan.core.database.entity.ModifierEntity
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
    fun getModifiers(): Flow<List<ModifierEntity>>
}