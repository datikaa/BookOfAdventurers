@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.compose.compiler)
    id("bookofadventurers.kmm.library")
}

android {
    namespace = "com.datikaa.bookofadventurers.core.design"
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
