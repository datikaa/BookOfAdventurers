plugins {
    id("bookofadventurers.kmm.compose")
    id("bookofadventurers.kmm.library")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.analytics"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.koin.androidx.compose)
}
