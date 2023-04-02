@file:Suppress("UnstableApiUsage")

package io.github.edwinchang24.peruse

import com.android.build.api.dsl.CommonExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

const val COMPILE_SDK = 33
const val MIN_SDK = 27
val JAVA_VERSION = JavaVersion.VERSION_17

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *>) = with(commonExtension) {
    compileSdk = COMPILE_SDK
    defaultConfig {
        minSdk = MIN_SDK
    }
    compileOptions {
        sourceCompatibility = JAVA_VERSION
        targetCompatibility = JAVA_VERSION
        isCoreLibraryDesugaringEnabled = true
    }
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
        jvmTarget = JAVA_VERSION.toString()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dependencies {
        add(
            "coreLibraryDesugaring",
            this@configureKotlinAndroid.extensions.getByType<VersionCatalogsExtension>().named("libs")
                .findLibrary("android.desugarJdkLibs").get()
        )
    }
    tasks.withType<Detekt>().configureEach {
        reports.md.required.set(true)
    }
    (this@configureKotlinAndroid as ExtensionAware).extensions.configure<DetektExtension>("detekt") {
        config = files(rootProject.file("detekt.yml"))
    }
}
