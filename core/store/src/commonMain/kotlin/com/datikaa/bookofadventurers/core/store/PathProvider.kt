package com.datikaa.bookofadventurers.core.store

import kotlinx.io.files.Path

interface PathProvider {
    val basePath: Path
}