plugins {
    java
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.2"
    id ("org.graalvm.buildtools.native") version "0.9.25"
    //id ("org.hibernate.orm") version "6.2.7.Final" // this works
    id ("org.hibernate.orm") version "6.3.0.Final" // this fails
}

group = "io.fouad"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")
}

hibernate {
    enhancement {
        enableLazyInitialization = true
        enableDirtyTracking = true
        enableAssociationManagement = true
        enableExtendedEnhancement = false
    }
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    builder.set("paketobuildpacks/builder-jammy-tiny:0.0.185")
    buildpacks.set(
        listOf(
            "gcr.io/paketo-buildpacks/graalvm:8.2.0",
            "gcr.io/paketo-buildpacks/java-native-image:8.17.0"
        )
    )
    imageName.set("${project.name}:${project.version}")
    environment.set(
        mapOf(
            "BP_JVM_VERSION" to "17"
        )
    )
}