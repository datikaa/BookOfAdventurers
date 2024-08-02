@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("bookofadventurers.kmm.compose")
    id("bookofadventurers.kmm.library")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.data"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:domain"))

    implementation(libs.koin.androidx.compose)
}
