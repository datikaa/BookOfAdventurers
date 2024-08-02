plugins {
    `kotlin-dsl`
}

group = "com.datikaa.bookofadventurers.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "bookofadventurers.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("kmmApplication") {
            id = "bookofadventurers.kmm.application"
            implementationClass = "KmmApplicationConventionPlugin"
        }
        register("kmmLibrary") {
            id = "bookofadventurers.kmm.library"
            implementationClass = "KmmLibraryConventionPlugin"
        }
    }
}
