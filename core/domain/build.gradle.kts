@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("boa.android.library")
}

android {
    namespace = "com.datikaa.boa.core.domain"

}

dependencies {
    implementation(libs.junit4)

    testImplementation(libs.kotest.assertions.core)
}
