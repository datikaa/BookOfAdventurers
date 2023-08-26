@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.realm)
    id("bookofadventurers.android.library")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.database"
}

dependencies {
    implementation(libs.koin.android)
    api(libs.realm.base)
}
