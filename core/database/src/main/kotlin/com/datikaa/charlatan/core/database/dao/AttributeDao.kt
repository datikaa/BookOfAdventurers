package com.datikaa.charlatan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.datikaa.charlatan.core.database.entity.AttributeEntity
import com.datikaa.charlatan.core.database.partial.AttributeEntityPartialUpdate
import kotlinx.coroutines.flow.Flow

@Dao
interface AttributeDao {

    @Insert
    suspend fun insertAttribute(characterAttribute: AttributeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAttributes(characterAttributes: List<AttributeEntity>)

    @Update(entity = AttributeEntity::class)
    suspend fun updateAttribute(characterAttribute: AttributeEntityPartialUpdate)

    @Query("DELETE FROM AttributeEntity")
    suspend fun deleteAttributes()

    @Query("SELECT * FROM AttributeEntity WHERE characterId = :characterId AND type = :type")
    suspend fun getAttribute(characterId: Int, type: AttributeEntity.Type): AttributeEntity

    @Query("SELECT * FROM AttributeEntity")
    fun flowAttributes(): Flow<List<AttributeEntity>>
}