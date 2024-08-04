dependencies {
    implementation(project(":peacock-core"))
    implementation(project(":peacock-support"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.session:spring-session-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.security:spring-security-test")
}

tasks.bootJar {
    enabled = true
    archiveVersion.set("")
}

tasks.jar {
    enabled = false
}
