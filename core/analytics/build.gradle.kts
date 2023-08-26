plugins {
    id("bookofadventurers.android.library")
    id("bookofadventurers.android.library.compose")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.analytics"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.koin.core)
    implementation(platform(libs.firebase.bom))
}
