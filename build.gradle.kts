plugins {
    kotlin("jvm") version "2.0.21"
    id("com.vanniktech.maven.publish") version "0.33.0"
}

val groupName: String by project
val versionName: String by project
val artifactName: String by project

group = groupName
version = versionName

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
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
