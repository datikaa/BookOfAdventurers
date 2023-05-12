package com.datikaa.charlatan.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AttributeDao {

    @Insert
    fun insertAttribute(characterAttribute: CharAttribute)

    @Update
    fun updateAttribute(characterAttribute: CharAttribute)

    @Query("SELECT * FROM CharAttribute")
    fun getAttributes(): Flow<List<CharAttribute>>

    @Query("DELETE FROM CharAttribute")
    fun deleteAttributes()
}