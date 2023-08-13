package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.classes.mapToDomain
import com.datikaa.bookofadventurers.core.database.dao.ClassDao
import com.datikaa.bookofadventurers.core.domain.BoaClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClassesRepositoryImpl(
    private val classDao: ClassDao,
) : ClassesRepository {

    override fun flowAllClasses(): Flow<List<BoaClass>> = classDao.flowClasses().map {
        it.mapToDomain()
    }
}
