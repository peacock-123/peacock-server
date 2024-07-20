dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")

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
