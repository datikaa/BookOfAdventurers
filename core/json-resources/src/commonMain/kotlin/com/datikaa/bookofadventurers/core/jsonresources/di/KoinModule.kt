package com.datikaa.bookofadventurers.core.jsonresources.di

import org.koin.dsl.module

val jsonResourcesKoinModule = module {
    jsonResourcesFactory()
}
