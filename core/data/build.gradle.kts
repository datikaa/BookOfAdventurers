@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("charlatan.android.library")
    id("charlatan.android.library.compose")
}

android {
    namespace = "com.datikaa.charlatan.core.data"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:domain"))

    implementation(libs.koin.androidx.compose)
}