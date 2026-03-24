package com.datikaa.bookofadventurers.core.database.prefill

import android.content.Context
import dev.icerock.moko.resources.FileResource

class AndroidJsonResourceFunctions(private val context: Context) : JsonResourceFunctions {

    override fun loadJsonArray(fileResource: FileResource): String = fileResource.readText(context)
}
