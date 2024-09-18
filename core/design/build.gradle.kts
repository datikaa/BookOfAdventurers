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
