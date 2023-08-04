@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.ksp)
    id("boa.android.library")
}

android {
    namespace = "com.datikaa.boa.core.database"

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(libs.koin.android)

    ksp(libs.room.compiler)
    api(libs.room.ktx)
    api(libs.room.runtime)
}
