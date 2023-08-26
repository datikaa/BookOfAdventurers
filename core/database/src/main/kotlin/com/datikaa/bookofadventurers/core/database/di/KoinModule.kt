package com.datikaa.bookofadventurers.core.database.di

import com.datikaa.bookofadventurers.core.database.realm.RealmInit
import io.realm.kotlin.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseKoinModule = module {
    single<Realm> { RealmInit.open(androidContext()) }
}
