@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("bookofadventurers.android.library")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.domain"
}

dependencies {
    testImplementation(libs.junit4)
    testImplementation(libs.kotest.assertions.core)
}
