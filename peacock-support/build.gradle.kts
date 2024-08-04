dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":peacock-core"))
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
