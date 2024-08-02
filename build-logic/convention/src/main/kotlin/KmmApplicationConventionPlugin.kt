import com.android.build.api.dsl.ApplicationExtension
import com.datikaa.bookofadventurers.Config
import com.datikaa.bookofadventurers.configureKmm
import com.datikaa.bookofadventurers.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KmmApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.multiplatform")
            }

            configureKmm()

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Config.targetSdk
            }
        }
    }
}
