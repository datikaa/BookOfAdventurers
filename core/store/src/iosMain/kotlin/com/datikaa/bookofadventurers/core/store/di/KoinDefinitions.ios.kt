package com.datikaa.bookofadventurers.core.store.di

import com.datikaa.bookofadventurers.core.store.PathProvider
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.io.files.Path
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun Module.pathProvider(): KoinDefinition<PathProvider> = factory {
    val fileManager: NSFileManager = NSFileManager.defaultManager
    val documentsUrl: NSURL = fileManager.URLForDirectory(
        directory = NSDocumentDirectory,
        appropriateForURL = null,
        create = false,
        inDomain = NSUserDomainMask,
        error = null
    )!!

    return@factory object : PathProvider {
        override val basePath: Path = Path(documentsUrl.path!!)
    }
}