import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    var composeVersion: String by rootProject.extra
    composeVersion = "1.4.0"
}

plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("it.nicolasfarabegoli.conventional-commits") version "3.1.0" apply true
    id("io.gitlab.arturbosch.detekt") version "1.22.0" apply true
}

tasks.withType<Detekt>().configureEach {
    reports {
        md.required.set(true)
    }
}
