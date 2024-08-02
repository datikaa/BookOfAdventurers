import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    id("bookofadventurers.kmm.library")
}

kotlin {
    configureAppleFrameworks {
        baseName = "domain"
        isStatic = true
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotest.assertions.core)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers.core.domain"
}
