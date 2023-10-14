package com.datikaa.bookofadventurers.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.datikaa.bookofadventurers.core.database.crossref.CharacterClassCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.CharacterSelectedClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.entity.CharacterEntity
import com.datikaa.bookofadventurers.core.database.relation.CharacterWithAbilitiesAndModifiers
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
    fun flowCharacterWithAbilitiesAndModifiers(id: Int): Flow<CharacterWithAbilitiesAndModifiers>

    @Insert
    suspend fun insertCharacterClassCrossRef(characterClassCrossRef: CharacterClassCrossRef): Long

    @Insert
    suspend fun insertCharacterSelectedClassModifierCrossRef(characterSelectedClassModifierCrossRef: CharacterSelectedClassModifierCrossRef): Long

    @Transaction
    suspend fun insertCharacterSelectedClassModifierCrossRefs(characterSelectedClassModifierCrossRefs: List<CharacterSelectedClassModifierCrossRef>) {
        characterSelectedClassModifierCrossRefs.forEach {
            insertCharacterSelectedClassModifierCrossRef(it)
        }
    }
}
