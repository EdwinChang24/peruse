import com.android.build.gradle.TestExtension
import io.github.edwinchang24.peruse.COMPILE_SDK
import io.github.edwinchang24.peruse.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.test")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("io.gitlab.arturbosch.detekt")
        extensions.configure<TestExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = COMPILE_SDK
        }
    }
}
