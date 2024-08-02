import com.datikaa.bookofadventurers.configureAppleFrameworks

plugins {
    id("bookofadventurers.kmm.compose")
    id("bookofadventurers.kmm.library")
}

kotlin {
    configureAppleFrameworks {
        baseName = "design"
        isStatic = true
    }

    sourceSets {
        androidMain.dependencies {
            // TODO migrate to kmm previews
            implementation(libs.androidx.compose.ui.tooling.preview)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3)
            implementation(compose.components.uiToolingPreview)
        }
    }
}

android {
    namespace = "com.datikaa.bookofadventurers.core.design"
}
