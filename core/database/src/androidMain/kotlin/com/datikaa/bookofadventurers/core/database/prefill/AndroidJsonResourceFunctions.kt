package com.datikaa.bookofadventurers.core.database.prefill

import android.content.Context
import androidx.annotation.RawRes
import com.datikaa.bookofadventurers.Res
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity
import dev.icerock.moko.resources.FileResource
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.KoinContext
import java.io.BufferedReader

@Deprecated("Migrating to Moko for Kmm")
internal fun loadJsonArray(
    context: Context,
    @RawRes rawResourceId: Int,
): JSONArray {

    val inputStream = context.resources.openRawResource(rawResourceId)

    BufferedReader(inputStream.reader()).use {
        return JSONArray(it.readText())
    }
}

class AndroidJsonResourceFunctions(private val context: Context) : JsonResourceFunctions {

    override fun loadJsonArray(fileResource: FileResource): String = fileResource.readText(context)
}
