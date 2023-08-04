package com.datikaa.boa

import com.datikaa.boa.di.appModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class KoinModuleTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun testKoinModules() {
        appModule.verify()
    }
}
