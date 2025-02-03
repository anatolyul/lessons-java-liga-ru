plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.hofftech"
version = "1.0-SNAPSHOT"

object Version {
    const val SPRING_BOOT = "3.4.0"
    const val TELEGRAM_BOTS = "6.9.7.1"
    const val LOMBOK_CORE = "1.18.34"
    const val ASSERTJ_CORE = "3.26.3"
    const val COMMONS_IO = "2.18.0"

    object Junit {
        const val BOM = "5.10.0"
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

    implementation("org.telegram:telegrambots-spring-boot-starter:${Version.TELEGRAM_BOTS}")
    implementation("commons-io:commons-io:${Version.COMMONS_IO}")

    implementation("org.projectlombok:lombok:${Version.LOMBOK_CORE}")
    compileOnly("org.projectlombok:lombok:${Version.LOMBOK_CORE}")
    annotationProcessor("org.projectlombok:lombok:${Version.LOMBOK_CORE}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Version.SPRING_BOOT}")
    testImplementation(platform("org.junit:junit-bom:${Version.Junit.BOM}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:${Version.ASSERTJ_CORE}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}