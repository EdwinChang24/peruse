import com.android.build.gradle.LibraryExtension
import io.github.edwinchang24.peruse.COMPILE_SDK
import io.github.edwinchang24.peruse.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

@Suppress("unused")
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.library")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("io.gitlab.arturbosch.detekt")
        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = COMPILE_SDK
        }
        configurations.configureEach {
            resolutionStrategy {
                force(extensions.getByType<VersionCatalogsExtension>().named("libs").findLibrary("junit4").get())
            }
        }
        dependencies {
            add("androidTestImplementation", kotlin("test"))
            add("testImplementation", kotlin("test"))
        }
    }
}
