package com.datikaa.bookofadventurers.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.datikaa.bookofadventurers.core.database.crossref.ClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
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

    @Insert
    suspend fun insertClassModifierCrossRef(classModifierCrossRef: ClassModifierCrossRef): Long
}
