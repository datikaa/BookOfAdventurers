package com.datikaa.bookofadventurers.core.store

import com.datikaa.bookofadventurers.core.store.models.CharacterEntity
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import kotlinx.io.files.Path

class CharacterStore(pathProvider: PathProvider) {
    val store: KStore<List<CharacterEntity>> = storeOf(
        file = Path("${pathProvider.basePath}/character.json"),
        version = 0,
    )
}

