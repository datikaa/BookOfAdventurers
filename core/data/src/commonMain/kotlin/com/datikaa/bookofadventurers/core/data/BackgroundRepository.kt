package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.domain.Background
import kotlinx.coroutines.flow.Flow

interface BackgroundRepository {
    suspend fun insertBackground(background: Background): Long

    fun flowAllBackgrounds(): Flow<List<Background>>

    fun flowAllBackgroundsWithModifiers(): Flow<List<Background>>

    suspend fun getAllBackgroundsWithModifiers(): List<Background>

    suspend fun associateBackgroundWithModifier(backgroundId: Long, modifierIds: List<Long>)
}
