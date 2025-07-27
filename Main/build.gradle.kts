plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}