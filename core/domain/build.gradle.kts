@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("bookofadventurers.kmm.library")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.domain"

}

dependencies {
    implementation(libs.junit4)

    testImplementation(libs.kotest.assertions.core)
}
