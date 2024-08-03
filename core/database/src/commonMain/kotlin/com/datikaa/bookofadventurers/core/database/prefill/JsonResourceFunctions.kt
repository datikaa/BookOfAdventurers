package com.datikaa.bookofadventurers.core.database.prefill

import dev.icerock.moko.resources.FileResource

interface JsonResourceFunctions {
    fun loadJsonArray(fileResource: FileResource): String
}
