package com.datikaa.charlatan.feature.attributes.domain

import com.datikaa.charlatan.core.database.CmmDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlowListOfCharactersUseCase(
    private val database: CmmDatabase,
) {

    operator fun invoke(): Flow<List<Character>> =
        database.characterDao().getCharacters().map { characters ->
            characters.map {
                Character(
                    id = it.id,
                    name = it.name,
                )
            }
        }
}