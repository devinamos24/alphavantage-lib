plugins {
    kotlin("multiplatform") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("org.jetbrains.dokka") version "1.9.20"
    id("maven-publish")
    id("com.android.library") version "8.1.2"
}

android {
    namespace = "com.jinxservers.alphavantage"
    compileSdk = 33
    defaultConfig {

        minSdk = 21
        version = 1

    }
}

group = "com.jinxservers"
version = "0.2.0-alpha"

val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

repositories {
    mavenCentral()
    google()
}

publishing {
    publications.withType<MavenPublication> {
        artifact(javadocJar)
        pom {

            groupId = "com.jinxservers"
            artifactId = "alphavantage-lib"
            version = version


            name = "Alpha Vantage API Wrapper"
            description = "A library used to make API calls to the Alpha Vantage API"
            url = "https://github.com/devinamos24/alphavantage-lib"
            licenses {
                license {
                    name = "The Apache License, Version 2.0"
                    url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                }
            }
            developers {
                developer {
                    name = "Devin Amos"
                    email = "devin.amos24@gmail.com"
                    organization = "Devin Amos"
                    organizationUrl = "https://github.com/devinamos24"
                }
            }
            scm {
                connection = "scm:git:git://github.com/devinamos24/alphavantage-lib.git"
                developerConnection = "scm:git:ssh://github.com:devinamos24/alphavantage-lib.git"
                url = "http://github.com/devinamos24/alphavantage-lib/tree/master"
            }
        }
    }
}

val kotlinJsTargetAttribute: Attribute<String> = Attribute.of("kotlinJsTarget", String::class.java)

kotlin {

    val ktorVersion: String by project

    explicitApi()

    applyDefaultHierarchyTemplate()

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()

    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    macosX64()
    macosArm64()

//    linuxX64 {
//        compilations.all {
//            cinterops {
//                val libcurl by creating
//            }
//        }
//        binaries {
//            sharedLib {
//                baseName = "native"
//            }
//        }
//    }

    mingwX64()

    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    jvm {
        jvmToolchain(8)
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }

//    js("jsBrowser", IR) {
//        browser()
//        attributes.attribute(kotlinJsTargetAttribute, targetName)
//    }
//    js("jsNode", IR) {
//        nodejs()
//        attributes.attribute(kotlinJsTargetAttribute, targetName)
//    }

    js {
        nodejs()
    }

    sourceSets.all {
        val suffixIndex = name.indexOfLast { it.isUpperCase() }
        val targetName = name.substring(0, suffixIndex)
        val suffix = name.substring(suffixIndex).lowercase().takeIf { it != "main" }
        kotlin.srcDir("src/$targetName/${suffix ?: "src"}")
        resources.srcDir("src/$targetName/${suffix?.let { it + "Resources" } ?: "resources"}")
        languageSettings {
//                        progressiveMode = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                implementation("org.slf4j:slf4j-api:2.0.9")
                implementation("org.slf4j:slf4j-jdk14:2.0.9")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }

        commonTest {
            dependencies {
                //TODO: Find out why this notation won't work for intellisense
//                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlin:kotlin-test:1.9.23")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
            }
        }

        jvmMain {
            dependencies {
                implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion")
            }
        }

//        val jsMain by creating {
//            dependsOn(commonMain.get())
//            dependencies {
//                implementation("io.ktor:ktor-client-js:$ktorVersion")
//            }
//        }
//        val jsTest by creating {
//            dependsOn(commonTest.get())
//        }
//
//        val jsBrowserMain by getting {
//            dependsOn(jsMain)
//            sourceSets["jsBrowserMain"].dependsOn(this)
//        }
//
//        val jsBrowserTest by getting {
//            dependsOn(jsTest)
//            sourceSets["jsBrowserTest"].dependsOn(this)
//        }
//
//        val jsNodeMain by getting {
//            dependsOn(jsMain)
//            sourceSets["jsNodeMain"].dependsOn(this)
//        }
//
//        val jsNodeTest by getting {
//            dependsOn(jsTest)
//            sourceSets["jsNodeTest"].dependsOn(this)
//        }

        //FIXME: Linux binaries will not compile due to the ld.gold linker being used. Windows cannot use elf objects.
//        val linuxMain by creating {
//            dependsOn(nativeMain)
//            linuxTargets.forEach { sourceSets["${it}Main"].dependsOn(this) }
//            dependencies {
//                implementation("io.ktor:ktor-client-curl:$ktorVersion")
//            }
//        }

    }
}
