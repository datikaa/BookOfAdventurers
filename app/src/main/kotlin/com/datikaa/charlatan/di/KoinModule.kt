package com.datikaa.charlatan.di

import com.datikaa.charlatan.core.database.di.databaseKoinModule
import com.datikaa.charlatan.feature.editor.di.editorKoinModule
import com.datikaa.charlatan.feature.overview.di.overviewKoinModule
import com.datikaa.charlatan.launcher.di.launcherKoinModule
import com.datikaa.charlatan.modifier.di.modifierKoinModule
import com.datikaa.core.data.di.dataModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {
    includeCoreModules()
    includeFeatureModules()
}

private fun Module.includeCoreModules() = includes(
    dataModule,
    editorKoinModule,
    databaseKoinModule,
)

private fun Module.includeFeatureModules() = includes(
    editorKoinModule,
    overviewKoinModule,
    modifierKoinModule,
    launcherKoinModule,
)
