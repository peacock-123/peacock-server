plugins {
    id("org.jooq.jooq-codegen-gradle") version "3.19.10"
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

jooq {
    configuration {
        jdbc {
            val dbUrl: String by project
            val dbUser: String by project
            val dbPassword: String by project

            driver = "org.postgresql.Driver"
            url = dbUrl
            user = dbUser
            password = dbPassword
        }

        generator {
            name = "org.jooq.codegen.KotlinGenerator"

            generate {
                isDeprecated = false
                isTables = true
                isIndexes = false
                isKotlinNotNullPojoAttributes = true
                isKotlinNotNullRecordAttributes = true
                isKotlinNotNullInterfaceAttributes = true
                isKotlinDefaultedNullablePojoAttributes = false
                isKotlinDefaultedNullableRecordAttributes = false
            }

            database {
                name = "org.jooq.meta.postgres.PostgresDatabase"
                includes = ".*"
                inputSchema = "public"
            }

            target {
                packageName = "com.peacock.core.mata"
                directory = "build/generated-sources/jooq"
            }
        }
    }
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlin", "build/generated-sources/jooq")
        }
    }
}
