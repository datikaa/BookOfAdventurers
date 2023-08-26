package com.datikaa.bookofadventurers.core.database.realm

import android.content.Context
import com.datikaa.bookofadventurers.core.database.prefill.PreloadDb
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmInit {

    fun open(context: Context): Realm {
        val config = RealmConfiguration.Builder(realmSchema)
            .deleteRealmIfMigrationNeeded()
            .initialData(PreloadDb(context))
            .schemaVersion(2)
            .build()
        return Realm.open(config)
    }
}
