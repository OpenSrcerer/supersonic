plugins {
    kotlin("jvm") version "1.9.20"
    id("com.google.devtools.ksp") version "1.9.20-1.0.14"
    application
}

group = "danielstefani.online"
version = "2.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.17")
    implementation("com.github.minndevelopment:jda-reactor:1.6.0")
    implementation("com.github.minndevelopment:jda-ktx:9370cb13cc64646862e6f885959d67eb4b157e4a")
    implementation("ch.qos.logback:logback-classic:1.2.9")

    implementation("io.insert-koin:koin-core:3.5.0")
    implementation("io.insert-koin:koin-annotations:1.3.0")
    ksp("io.insert-koin:koin-ksp-compiler:1.3.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}