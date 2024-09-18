package com.datikaa.bookofadventurers.core.database.prefill

import dev.icerock.moko.resources.FileResource

class IosJsonResourceFunctions : JsonResourceFunctions {

    override fun loadJsonArray(fileResource: FileResource): String = fileResource.readText()
}
