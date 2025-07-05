package com.datikaa.bookofadventurers.di

import com.bookofadventurers.feature.background.di.backgroundsKoinModule
import com.bookofadventurers.feature.wizard.di.wizardKoinModule
import com.datikaa.bookofadventurers.core.analytics.di.analyticsModule
import com.datikaa.bookofadventurers.core.data.di.dataModule
import com.datikaa.bookofadventurers.core.database.di.databaseKoinModule
import com.datikaa.bookofadventurers.core.store.di.storeModule
import com.datikaa.bookofadventurers.feature.editor.di.editorKoinModule
import com.datikaa.bookofadventurers.feature.overview.di.overviewKoinModule
import com.datikaa.bookofadventurers.launcher.di.launcherKoinModule
import com.datikaa.bookofadventurers.modifier.di.modifierKoinModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {
    includeCoreModules()
    includeFeatureModules()
}

private fun Module.includeCoreModules() = includes(
    analyticsModule,
    dataModule,
    editorKoinModule,
    databaseKoinModule,
    storeModule,
)

private fun Module.includeFeatureModules() = includes(
    backgroundsKoinModule,
    editorKoinModule,
    launcherKoinModule,
    modifierKoinModule,
    overviewKoinModule,
    wizardKoinModule,
)
