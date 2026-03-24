package com.datikaa.bookofadventurers.core.store.di

import com.datikaa.bookofadventurers.core.store.PathProvider
import kotlinx.io.files.Path
import org.koin.android.ext.koin.androidContext
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

actual fun Module.pathProvider(): KoinDefinition<PathProvider> = factory {
    return@factory object : PathProvider {
        override val basePath: Path = Path(androidContext().filesDir.path)
    }
}

