plugins {
    kotlin("jvm") version "2.0.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jsoup:jsoup:1.15.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}
