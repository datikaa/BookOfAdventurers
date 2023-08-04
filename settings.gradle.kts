pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "bookofadventurers"

include(":app")
include(":core:data")
include(":core:database")
include(":core:design")
include(":core:domain")
include(":feature:editor")
include(":feature:launcher")
include(":feature:modifier")
include(":feature:overview")
