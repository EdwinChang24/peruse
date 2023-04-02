import com.android.build.api.dsl.ApplicationExtension
import io.github.edwinchang24.peruse.COMPILE_SDK
import io.github.edwinchang24.peruse.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("io.gitlab.arturbosch.detekt")
        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = COMPILE_SDK
        }
    }
}
