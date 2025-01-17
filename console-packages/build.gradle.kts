plugins {
    id("java")
}

group = "ru.hofftech"
version = "1.0-SNAPSHOT"

object Version {
    const val SLF4J_API = "2.0.7"
    const val JACKSON_DATABIND = "2.13.4.2"
    const val OPENCSV = "5.9"
    const val TELEGRAM_BOTS = "6.9.7.1"
    const val LOMBOK_CORE = "1.18.34"
    const val ASSERTJ_CORE = "3.26.3"
    const val SNAKEYAML = "1.33"

    object Junit {
        const val BOM = "5.10.0"
    }

    object Logback {
        const val CORE = "1.5.13"
        const val CLASSIC = "1.4.12"
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.slf4j:slf4j-api:${Version.SLF4J_API}")
    implementation("ch.qos.logback:logback-core:${Version.Logback.CORE}")
    implementation("ch.qos.logback:logback-classic:${Version.Logback.CLASSIC}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${Version.JACKSON_DATABIND}")
    implementation("com.opencsv:opencsv:${Version.OPENCSV}")
    implementation("org.telegram:telegrambots:${Version.TELEGRAM_BOTS}")
    implementation("org.yaml:snakeyaml:${Version.SNAKEYAML}")

    implementation("org.projectlombok:lombok:${Version.LOMBOK_CORE}")
    compileOnly("org.projectlombok:lombok:${Version.LOMBOK_CORE}")
    annotationProcessor("org.projectlombok:lombok:${Version.LOMBOK_CORE}")

    testImplementation(platform("org.junit:junit-bom:${Version.Junit.BOM}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:${Version.ASSERTJ_CORE}")
}

tasks.test {
    useJUnitPlatform()
}