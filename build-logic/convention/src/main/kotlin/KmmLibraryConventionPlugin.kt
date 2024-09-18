import com.android.build.gradle.LibraryExtension
import com.datikaa.bookofadventurers.Config
import com.datikaa.bookofadventurers.configureKmm
import com.datikaa.bookofadventurers.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KmmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
            }

            configureKmm()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Config.targetSdk
            }
        }
    }
}
