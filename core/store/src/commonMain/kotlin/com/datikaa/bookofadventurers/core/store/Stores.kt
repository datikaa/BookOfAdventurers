package com.datikaa.bookofadventurers.core.store

import com.datikaa.bookofadventurers.core.jsonresources.JsonResources
import com.datikaa.bookofadventurers.core.store.models.BackgroundEntity
import com.datikaa.bookofadventurers.core.store.models.CharacterEntity
import com.datikaa.bookofadventurers.core.store.models.ClassEntity
import com.datikaa.bookofadventurers.core.store.models.ModifierEntity
import dev.icerock.moko.resources.FileResource
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import kotlinx.io.files.Path
import kotlinx.serialization.json.Json

class CharacterStore(pathProvider: PathProvider) {
    val store: KStore<List<CharacterEntity>> = storeOf(
        file = Path("${pathProvider.basePath}/character.json"),
        version = 0,
        default = emptyList(),
    )
}

class ModifierStore(pathProvider: PathProvider) {
    val store: KStore<List<ModifierEntity>> = storeOf(
        file = Path("${pathProvider.basePath}/modifier.json"),
        version = 0,
        default = emptyList(),
    )
}

class BackgroundStore(pathProvider: PathProvider, jsonResources: JsonResources) {
    val store: KStore<List<BackgroundEntity>> = storeOf(
        file = Path("${pathProvider.basePath}/background.json"),
        version = 0,
        default = jsonResources.parseJsonFromMoko(Res.files.backgrounds_json),
    )
}

class ClassStore(pathProvider: PathProvider, jsonResources: JsonResources) {
    val store: KStore<List<ClassEntity>> = storeOf(
        file = Path("${pathProvider.basePath}/class.json"),
        version = 0,
        default = jsonResources.parseJsonFromMoko(Res.files.classes_json),
    )
}

private inline fun <reified T> JsonResources.parseJsonFromMoko(fileResource: FileResource): T {
    val raw = loadJsonArray(fileResource)
    return json.decodeFromString<T>(raw)
}

private val json = Json {
    ignoreUnknownKeys = true
}
