plugins {
    id("com.google.cloud.tools.jib") version "3.4.3"
}

dependencies {
    implementation(project(":peacock-core"))
    implementation(project(":peacock-support"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.session:spring-session-data-redis")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.ehcache:ehcache:3.10.8")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testRuntimeOnly("com.h2database:h2")

    testImplementation("org.flywaydb:flyway-core")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.23")
}

jib {
    from {
        image = "openjdk:21-jdk-slim"
    }

    to {
        image = "ghcr.io/peacock-123/peacock-server"
        tags = setOf("latest", System.getenv("GITHUB_SHA"))
        auth {
            username = System.getenv("GH_USERNAME")
            password = System.getenv("GH_TOKEN")
        }
    }

    container {
        ports = listOf("8080")
        jvmFlags =
            listOf(
                "-Xms512m",
                "-Xmx2048m",
                "-XX:+UseContainerSupport",
                "-XX:MaxRAMPercentage=75",
                "-XX:+UseG1GC",
                "-XX:MaxGCPauseMillis=200",
                "-Djava.security.egd=file:/dev/./urandom",
            )
        environment =
            mapOf(
                "SPRING_PROFILES_ACTIVE" to System.getenv("SPRING_PROFILES_ACTIVE"),
            )
        user = "nobody:nogroup"
        creationTime = "USE_CURRENT_TIMESTAMP"
    }

    containerizingMode = "packaged"
}

tasks.bootJar {
    enabled = true
    archiveVersion.set("")
}

tasks.jar {
    enabled = false
}
