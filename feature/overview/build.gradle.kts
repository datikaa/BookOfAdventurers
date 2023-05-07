@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("charlatan.android.library")
    id("charlatan.android.library.compose")
}

android {
    namespace = "com.datikaa.charlatan.feature.overview"
}

dependencies {

    implementation(project(":core:design"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)

    implementation(libs.koin.androidx.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
}