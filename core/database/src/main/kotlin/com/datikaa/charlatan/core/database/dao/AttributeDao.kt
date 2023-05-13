package com.datikaa.charlatan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.datikaa.charlatan.core.database.entity.AttributeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AttributeDao {

    @Insert
    suspend fun insertAttribute(characterAttribute: AttributeEntity)

    @Update
    suspend fun updateAttribute(characterAttribute: AttributeEntity)

    @Query("DELETE FROM AttributeEntity")
    suspend fun deleteAttributes()

    @Query("SELECT * FROM AttributeEntity")
    fun getAttributes(): Flow<List<AttributeEntity>>
}