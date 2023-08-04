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
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.firebase.analytics)
    implementation(libs.koin.androidx.compose)
}
