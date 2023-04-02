import com.android.build.api.dsl.ApplicationExtension
import io.github.edwinchang24.peruse.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")
        configureAndroidCompose(target.extensions.getByType<ApplicationExtension>())
    }
}
