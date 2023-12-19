val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val exposed_version: String by project
val h2_version: String by project

application {
    mainClass.set("io.kl3jvi.ApplicationKt")
}

plugins {
    kotlin("jvm") version "1.9.21"
    id("io.ktor.plugin") version "2.3.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}



group = "io.kl3jvi"
version = "0.0.1"

application {
    mainClass = "io.kl3jvi.ApplicationKt"
}

repositories {
    mavenCentral()
}

tasks.register("stage") {
    dependsOn("build", "clean")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-client-core-jvm")
    implementation("io.ktor:ktor-client-apache-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-default-headers-jvm")
    implementation("io.ktor:ktor-server-swagger-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    // koin for ktor backend
    implementation("io.ktor:ktor-server-core-jvm")
    // mongo for ktor
    implementation("org.litote.kmongo:kmongo-coroutine:4.11.0")

    implementation("io.insert-koin:koin-core:3.1.2")
    implementation("io.insert-koin:koin-ktor:3.1.2")
    implementation("io.ktor:ktor-auth-jwt:1.6.7")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    // bcrypt
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("com.h2database:h2:$h2_version")
//    netty
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:2.3.6")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

tasks {
    shadowJar {
        archiveBaseName.set("backend")
        archiveClassifier.set("")
        archiveVersion.set("")
        isZip64 = true
        manifest {
            attributes(Pair("Main-Class", "io.kl3jvi.ApplicationKt"))
        }
        configurations = listOf(project.configurations.runtimeClasspath.get())
    }
}
