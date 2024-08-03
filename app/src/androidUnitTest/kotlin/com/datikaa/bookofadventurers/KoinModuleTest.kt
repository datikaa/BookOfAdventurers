package com.datikaa.bookofadventurers

import com.datikaa.bookofadventurers.di.appModule
import org.junit.Test
import org.koin.test.verify.verify

class KoinModuleTest {

    @Test
    fun testKoinModules() {
        appModule.verify()
    }
}
