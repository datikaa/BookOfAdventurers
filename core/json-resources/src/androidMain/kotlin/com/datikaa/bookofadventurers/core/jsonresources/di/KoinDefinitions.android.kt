package com.datikaa.bookofadventurers.core.jsonresources.di

import com.datikaa.bookofadventurers.core.jsonresources.AndroidJsonResources
import com.datikaa.bookofadventurers.core.jsonresources.JsonResources
import org.koin.android.ext.koin.androidApplication
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

internal actual fun Module.jsonResourcesFactory(): KoinDefinition<JsonResources> = factory {
    AndroidJsonResources(androidApplication())
}

