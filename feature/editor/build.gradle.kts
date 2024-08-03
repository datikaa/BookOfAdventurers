import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    id("bookofadventurers.kmm.compose")
    id("bookofadventurers.kmm.library")
}

kotlin {
    configureAppleFrameworks {
        baseName = "editor"
        isStatic = true
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data)
            implementation(projects.core.design)
            implementation(projects.core.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers.feature.editor"
}
