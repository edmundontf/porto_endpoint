plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.projeto_mentoria"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(18)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // Spring Data JPA
    implementation("org.springframework.boot:spring-boot-starter-web") // Spring Web
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // Suporte a JSON para Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect") // Reflexão do Kotlin
    runtimeOnly("com.h2database:h2") // Banco de dados H2 (em memória)
    testImplementation("org.springframework.boot:spring-boot-starter-test") // Testes
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
