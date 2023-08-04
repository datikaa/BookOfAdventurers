@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("boa.android.library")
    id("boa.android.library.compose")
}

android {
    namespace = "com.datikaa.boa.core.data"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:domain"))

    implementation(libs.koin.androidx.compose)
}
