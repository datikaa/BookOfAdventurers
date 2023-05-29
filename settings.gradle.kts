pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Charlatan"

include(":app")
include(":core:data")
include(":core:database")
include(":core:design")
include(":core:domain")
include(":feature:editor")
include(":feature:overview")
