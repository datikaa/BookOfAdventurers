package com.datikaa.charlatan.feature.character.domain

import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlowListAllCharactersUseCase(
    private val dataBase: CmmDatabase,
) {

    operator fun invoke(): Flow<List<CmmCharacter>> =
        dataBase.characterDao().flowCharacters().map { characters ->
            characters.map {
                CmmCharacter(
                    id = it.id,
                    name = it.name,
                )
            }
        }
}