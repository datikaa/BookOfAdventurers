package com.datikaa.bookofadventurers.core.jsonresources

import dev.icerock.moko.resources.FileResource

internal class IosJsonResources : JsonResources {

    override fun loadJsonArray(fileResource: FileResource): String = fileResource.readText()
}