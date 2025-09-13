import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform") version "2.0.21"
    id("com.android.library") version "8.5.2"
    id("com.vanniktech.maven.publish") version "0.33.0"
}

val groupName: String by project
val versionName: String by project
val artifactName: String by project

group = groupName
version = versionName

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    jvm()
    iosArm64()
    iosSimulatorArm64()
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs().browser()

    sourceSets {
        commonMain.dependencies {
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "com.tyme"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()
    coordinates(groupName, artifactName, versionName)

    pom {
        name.set(artifactName)
        description.set("a calendar library")
        url.set("https://github.com/6tail/$artifactName")
        licenses {
            license {
                name.set("The MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                name.set("6tail")
                email.set("6tail@6tail.cn")
                timezone.set("+8")
            }
        }
        scm {
            tag.set("master")
            url.set("git@github.com:6tail/$artifactName.git")
            connection.set("scm:git:git@github.com:6tail/$artifactName.git")
            developerConnection.set("scm:git:git@github.com:6tail/$artifactName.git")
        }
    }
}
