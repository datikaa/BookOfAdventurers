package com.datikaa.bookofadventurers.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.datikaa.bookofadventurers.core.database.crossref.BackgroundSkillProficiencyCrossRef
import com.datikaa.bookofadventurers.core.database.entity.BackgroundEntity
import com.datikaa.bookofadventurers.core.database.relation.ClassWithModifiers
import kotlinx.coroutines.flow.Flow

@Dao
interface BackgroundDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBackground(characterEntity: BackgroundEntity): Long

    @Update
    suspend fun updateBackground(characterEntity: BackgroundEntity)

    @Query("SELECT * FROM BackgroundEntity")
    fun flowBackgrounds(): Flow<List<BackgroundEntity>>

    @Query("SELECT * FROM BackgroundEntity")
    fun getBackgrounds(): List<BackgroundEntity>

    @Transaction
    @Query("SELECT * FROM BackgroundEntity")
    fun getClassesWithModifiers(): List<ClassWithModifiers>

    @Insert
    suspend fun insertBackgroundSkillProficiencyCrossRef(backgroundSkillProficiencyCrossRef: BackgroundSkillProficiencyCrossRef): Long
}
