import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var mockKVersion = "1.13.4"
plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
}

group = "me.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.kotest:kotest-runner-junit5-jvm:4.6.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.4")
    testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.kotest:kotest-property:5.5.4")

    implementation(kotlin("stdlib-jdk8"))

    implementation("de.m3y.kformat:kformat:0.9")

    implementation("io.mockk:mockk:$mockKVersion")

    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("org.slf4j:slf4j-api:2.0.3")}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
        md.required.set(true) // simple Markdown format
    }
}

application {
    mainClass.set("MainKt")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}