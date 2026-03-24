package com.datikaa.bookofadventurers.core.jsonresources

import dev.icerock.moko.resources.FileResource

interface JsonResources {
    fun loadJsonArray(fileResource: FileResource): String
}