dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    runtimeOnly("org.postgresql:postgresql")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
