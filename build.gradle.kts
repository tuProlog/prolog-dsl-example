plugins {
    java
    application
    kotlin("jvm") version "1.4.0"
}

group = "it.unibo.tuprolog.dsl"
version = "1.0-SNAPSHOT"

val ktFreeCompilerArgs: String by project
val tuPrologVersion: String by project

repositories {
    maven("https://dl.bintray.com/pika-lab/tuprolog/")
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    implementation("it.unibo.tuprolog", "dsl-solve-jvm", tuPrologVersion)
//    implementation("it.unibo.tuprolog", "parser-theory-jvm", tuPrologVersion)
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = ktFreeCompilerArgs.split(";").toList()
    }
}

application {
    mainClassName = "it.unibo.tuprolog.dsl.solve.ExampleKt"
}

tasks.getByName<JavaExec>("run") {
    standardInput = System.`in`
}