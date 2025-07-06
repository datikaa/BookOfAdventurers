package com.datikaa.bookofadventurers.core.jsonresources

import android.content.Context
import dev.icerock.moko.resources.FileResource

internal class AndroidJsonResources(private val context: Context) : JsonResources {

    override fun loadJsonArray(fileResource: FileResource): String = fileResource.readText(context)
}