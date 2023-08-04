package com.datikaa.boa.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.datikaa.boa.core.database.entity.AbilityEntity
import com.datikaa.boa.core.database.partial.AbilityEntityPartialUpdate
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityDao {

    @Insert
    suspend fun insertAbility(abilityEntity: AbilityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAbility(abilityEntities: List<AbilityEntity>)

    @Update(entity = AbilityEntity::class)
    suspend fun updateAbility(abilityEntityPartialUpdate: AbilityEntityPartialUpdate)

    @Query("DELETE FROM AbilityEntity")
    suspend fun deleteAllAbilities()

    @Query("SELECT * FROM AbilityEntity WHERE characterId = :characterId AND type = :type")
    suspend fun getAbility(characterId: Int, type: AbilityEntity.Type): AbilityEntity

    @Query("SELECT * FROM AbilityEntity")
    fun flowAbilities(): Flow<List<AbilityEntity>>
}
