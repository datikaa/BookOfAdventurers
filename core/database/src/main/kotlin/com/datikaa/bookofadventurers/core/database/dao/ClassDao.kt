package com.datikaa.bookofadventurers.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.datikaa.bookofadventurers.core.database.crossref.CharacterSelectedClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.ClassSavingThrowCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.ClassSkillProficiencyCrossRef
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
import com.datikaa.bookofadventurers.core.database.relation.ClassWithModifiers
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(characterEntity: ClassEntity): Long

    @Update
    suspend fun updateClass(characterEntity: ClassEntity)

    @Query("DELETE FROM ClassEntity")
    suspend fun deleteClasses()

    @Query("SELECT * FROM ClassEntity")
    fun flowClasses(): Flow<List<ClassEntity>>

    @Query("SELECT * FROM ClassEntity")
    fun getClasses(): List<ClassEntity>

    @Transaction
    @Query("SELECT * FROM ClassEntity")
    fun getClassesWithModifiers(): List<ClassWithModifiers>

    @Insert
    suspend fun insertClassSavingThrowCrossRef(classSavingThrowCrossRef: ClassSavingThrowCrossRef): Long

    @Insert
    suspend fun insertClassSkillProficiencyCrossRef(classSkillProficiencyCrossRef: ClassSkillProficiencyCrossRef): Long

    @Insert
    suspend fun insertCharacterSelectedClassModifierCrossRef(characterSelectedClassModifierCrossRef: CharacterSelectedClassModifierCrossRef): Long
}
