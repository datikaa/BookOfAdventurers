package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.domain.BoaClass
import kotlinx.coroutines.flow.Flow

interface ClassesRepository {

    fun flowAllClasses(): Flow<List<BoaClass>>
    suspend fun getAllClasses(): List<BoaClass>
}
