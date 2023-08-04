package com.datikaa.boa.di

import com.datikaa.boa.core.analytics.analyticsModule
import com.datikaa.boa.core.database.di.databaseKoinModule
import com.datikaa.boa.feature.editor.di.editorKoinModule
import com.datikaa.boa.feature.overview.di.overviewKoinModule
import com.datikaa.boa.launcher.di.launcherKoinModule
import com.datikaa.boa.modifier.di.modifierKoinModule
import com.datikaa.boa.core.data.di.dataModule
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
)

private fun Module.includeFeatureModules() = includes(
    editorKoinModule,
    overviewKoinModule,
    modifierKoinModule,
    launcherKoinModule,
)
