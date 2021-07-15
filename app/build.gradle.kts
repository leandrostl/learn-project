plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id("org.jetbrains.kotlin.kapt") version "1.5.10"
    id("io.micronaut.application") version "1.5.3"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.10"
    id("org.sonarqube") version "3.3"
    jacoco
    id("io.gitlab.arturbosch.detekt") version "1.17.1"
    id("org.jmailen.kotlinter") version "3.4.5"
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

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config = files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt

    reports {
        html.enabled = true // observe findings in your browser with structure and code snippets
        xml.enabled = true // checkstyle like format mainly for integrations like Jenkins
        txt.enabled = true // similar to the console output, contains issue signature to manually edit baseline files
        sarif.enabled = true // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
    }
}

dependencies {
    kapt("io.micronaut.openapi:micronaut-openapi")
    kapt("io.micronaut.security:micronaut-security-annotations")
    implementation("io.micronaut.security:micronaut-security-jwt")
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
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.17.1")
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

jacoco {
    toolVersion = "0.8.7"
    reportsDirectory.set(layout.buildDirectory.dir("$buildDir/reports/jacoco"))
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
    jacocoTestReport {
        reports {
            xml.required.set(true)
            html.outputLocation.set(layout.buildDirectory.dir("$buildDir/reports/jacocoHtml"))
        }
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
