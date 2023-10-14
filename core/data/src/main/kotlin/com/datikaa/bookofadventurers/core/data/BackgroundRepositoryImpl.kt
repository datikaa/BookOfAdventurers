package com.datikaa.bookofadventurers.core.data

import com.datikaa.bookofadventurers.core.data.adapter.background.mapToDomain
import com.datikaa.bookofadventurers.core.data.adapter.background.toEntity
import com.datikaa.bookofadventurers.core.database.crossref.BackgroundSkillProficiencyCrossRef
import com.datikaa.bookofadventurers.core.database.dao.BackgroundDao
import com.datikaa.bookofadventurers.core.domain.Background
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BackgroundRepositoryImpl(
    private val backgroundDao: BackgroundDao,
) : BackgroundRepository {

    override suspend fun insertBackground(background: Background): Long {
        return backgroundDao.insertBackground(background.toEntity())
    }

    override suspend fun associateBackgroundWithCharacter(backgroundId: Long, characterId: Long) {
        backgroundDao.insertBackgroundSkillProficiencyCrossRef(
            BackgroundSkillProficiencyCrossRef(
                backgroundId = backgroundId,
                modifierId = characterId
            )
        )
    }

    override fun flowAllBackgrounds(): Flow<List<Background>> {
        return backgroundDao.flowBackgrounds().map { it.mapToDomain() }
    }

    override suspend fun getAllBackgroundsWithModifiers(): List<Background> {
        return backgroundDao.getBackgroundsWithModifiers().mapToDomain()
    }
}