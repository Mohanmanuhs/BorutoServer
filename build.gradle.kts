val commons_codec_version: String by project
val ktor_version: String by project
val mongo_version: String by project

plugins {

    application
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization").version("1.9.22")
    id("io.ktor.plugin") version "2.3.7"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application{
    mainClass.set("io.ktor.server.netty.EngineMain")
}
repositories {
    mavenCentral()
}


dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.22")
    testImplementation("io.ktor:ktor-server-test-host:2.3.7")
    implementation("io.ktor:ktor-server-core-jvm:2.3.7")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.7")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.ktor:ktor-server-call-logging:2.3.7")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.8")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.8")
    implementation("io.ktor:ktor-server-html-builder:2.3.8")
    implementation("io.insert-koin:koin-ktor:3.5.1")
    implementation ("io.insert-koin:koin-logger-slf4j:3.5.1")
    implementation("io.ktor:ktor-server-default-headers:2.3.7")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.23")

    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("commons-codec:commons-codec:$commons_codec_version")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.1.2")

    implementation("org.mongodb:mongodb-driver-core:$mongo_version")
    implementation("org.mongodb:mongodb-driver-sync:$mongo_version")
    implementation("org.mongodb:bson:$mongo_version")


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}