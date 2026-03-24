package com.datikaa.bookofadventurers.core.jsonresources.di

import com.datikaa.bookofadventurers.core.jsonresources.IosJsonResources
import com.datikaa.bookofadventurers.core.jsonresources.JsonResources
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

internal actual fun Module.jsonResourcesFactory(): KoinDefinition<JsonResources> = factory {
    IosJsonResources()
}