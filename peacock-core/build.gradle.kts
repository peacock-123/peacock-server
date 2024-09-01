import org.jooq.meta.jaxb.Generate

plugins {
    id("dev.monosoul.jooq-docker") version "6.1.3"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jooq")

    jooqCodegen("org.postgresql:postgresql")

    runtimeOnly("org.postgresql:postgresql")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

tasks {
    generateJooqClasses {
        schemas = listOf("public")
        includeFlywayTable = false
        usingJavaConfig {
            name = "org.jooq.codegen.KotlinGenerator"

            generate =
                Generate().apply {
                    isDeprecated = false
                    isRecords = false
                    isTables = true
                    isIndexes = false
                    isKeys = false
                    isDefaultCatalog = false
                }
        }
    }
}
