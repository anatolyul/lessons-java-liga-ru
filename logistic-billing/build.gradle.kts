plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.hofftech"
version = "1.0-SNAPSHOT"

object Version {
    const val SPRING_BOOT = "3.4.0"
    const val SPRING_CLOUD = "4.2.0"
    const val SLF4J_API = "2.0.7"
    const val JACKSON_DATABIND = "2.18.2"
    const val OPENCSV = "5.9"
    const val TELEGRAM_BOTS = "6.9.7.1"
    const val LOMBOK_CORE = "1.18.34"
    const val ASSERTJ_CORE = "3.26.3"
    const val COMMONS_IO = "2.18.0"
    const val MAPSTRUCT = "1.6.3"
    const val OPENAPI = "2.8.3"
    const val CAFFEINE = "3.2.0"

    object Junit {
        const val BOM = "5.10.0"
    }

    object Logback {
        const val CORE = "1.5.13"
        const val CLASSIC = "1.4.12"
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:${Version.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-web:${Version.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Version.SPRING_BOOT}")

    implementation("org.springframework.cloud:spring-cloud-stream:${Version.SPRING_CLOUD}")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka:${Version.SPRING_CLOUD}")

    implementation("org.springframework.boot:spring-boot-starter-cache:${Version.SPRING_BOOT}")
    implementation("com.github.ben-manes.caffeine:caffeine:${Version.CAFFEINE}")

    implementation("org.projectlombok:lombok:${Version.LOMBOK_CORE}")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Version.OPENAPI}")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")

    implementation("org.mapstruct:mapstruct:${Version.MAPSTRUCT}")

    runtimeOnly("org.postgresql:postgresql")

    compileOnly("org.projectlombok:lombok:${Version.LOMBOK_CORE}")
    annotationProcessor("org.projectlombok:lombok:${Version.LOMBOK_CORE}")

    annotationProcessor("org.mapstruct:mapstruct-processor:${Version.MAPSTRUCT}")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:${Version.MAPSTRUCT}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Version.SPRING_BOOT}")
    testImplementation(platform("org.junit:junit-bom:${Version.Junit.BOM}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:${Version.ASSERTJ_CORE}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}