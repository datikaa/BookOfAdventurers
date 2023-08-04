plugins {
    id("boa.android.library")
    id("boa.android.library.compose")
}

android {
    namespace = "com.datikaa.boa.core.analytics"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.firebase.analytics)
    implementation(libs.koin.androidx.compose)
}
