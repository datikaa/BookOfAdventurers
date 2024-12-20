import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension

class KmmComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.compose")
            }

            setUpComposePreviews()
        }
    }

    private fun Project.setUpComposePreviews() {
        val composeExtension = extensions.getByType<ComposeExtension>()

        val debugImplementation = project.configurations.findByName("debugImplementation")
            ?: project.configurations.create("debugImplementation")

        // if i'll ever need it: "org.jetbrains.compose.ui:ui-tooling:<version>"
        project.dependencies.add(debugImplementation.name, composeExtension.dependencies.uiTooling)
    }
}
