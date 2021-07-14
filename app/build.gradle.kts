plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id("org.jetbrains.kotlin.kapt") version "1.5.10"
    id("io.micronaut.application") version "1.5.3"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.10"
    id("org.sonarqube") version "3.3"
}

version = "0.1"
group = "com.leandro"

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.leandro.*")
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "leandrostl_learn-project")
        property("sonar.organization", "leandrostl")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}


dependencies {
    kapt("io.micronaut.openapi:micronaut-openapi")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.10")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
}

application {
    // Define the main class for the application.
    mainClass.set("com.leandro.learnproject.Application")
}

java {
    sourceCompatibility = JavaVersion.toVersion("15")
}

kapt {
    arguments {
        arg("micronaut.openapi.views.spec", "redoc.enabled=true,rapidoc.enabled=true,swagger-ui.enabled=true,swagger-ui.theme=flattop")
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "15"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "15"
        }
    }
}
