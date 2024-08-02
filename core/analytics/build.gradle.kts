plugins {
    alias(libs.plugins.compose.compiler)
    id("bookofadventurers.kmm.library")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.analytics"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.koin.androidx.compose)
}
