rootProject.name = "common-api"
include("common")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("spring.boot.starter.web", "org.springframework.boot:spring-boot-starter-web:3.2.0")
            library("spring.boot.starter.parent", "org.springframework.boot:spring-boot-starter-parent:3.2.0")
            library("spring.kafka", "org.springframework.kafka:spring-kafka:3.2.0")
            library("commons.math", "org.apache.commons:commons-math3:3.2")
            library("immutables.value", "org.immutables:value:2.9.0")
            library("immutables.builder", "org.immutables:builder:2.9.0")
            library("immutables.annotations", "org.immutables:value-annotations:2.9.0")
            library("jackson", "com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.17.2")
        }

        create("testlibs") {
            library("spring.boot.starter.test", "org.springframework.boot:spring-boot-starter-test:3.2.0")
            library("junit.jupiter.api", "org.junit.jupiter:junit-jupiter-api:5.8.1")
            library("junit.jupiter.engine", "org.junit.jupiter:junit-jupiter-engine:5.8.1")
            library("spring.kafka.test", "org.springframework.kafka:spring-kafka:3.2.0")
            library("podam.random", "uk.co.jemos.podam:podam:7.1.0.RELEASE")
        }
    }
}