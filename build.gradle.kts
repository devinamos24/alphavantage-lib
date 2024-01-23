plugins {
    kotlin("multiplatform") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
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
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

val kotlinJsTargetAttribute: Attribute<String> = Attribute.of("kotlinJsTarget", String::class.java)

kotlin {

    val ktorVersion: String by project

    explicitApi()

    ios()
    iosSimulatorArm64()
    watchos()
    watchosSimulatorArm64()
    tvos()
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

    val darwinTargets = listOf("ios", "iosSimulatorArm64", "watchos", "watchosSimulatorArm64", "tvos", "tvosSimulatorArm64", "macosX64", "macosArm64")
    val linuxTargets = listOf("linuxX64")
    val mingwTargets = listOf("mingwX64")

    jvm {
        jvmToolchain(8)
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    js("jsBrowser", IR) {
        browser()
        attributes.attribute(kotlinJsTargetAttribute, targetName)
    }
    js("jsNode", IR) {
        nodejs()
        attributes.attribute(kotlinJsTargetAttribute, targetName)
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
        val commonMain by getting {
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

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion")
            }
        }

        val jvmTest by getting {

        }

        val jsMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
        val jsTest by creating {
            dependsOn(commonTest)
        }

        val jsBrowserMain by getting {
            dependsOn(jsMain)
            sourceSets["jsBrowserMain"].dependsOn(this)
        }

        val jsBrowserTest by getting {
            dependsOn(jsTest)
            sourceSets["jsBrowserTest"].dependsOn(this)
        }

        val jsNodeMain by getting {
            dependsOn(jsMain)
            sourceSets["jsNodeMain"].dependsOn(this)
        }

        val jsNodeTest by getting {
            dependsOn(jsTest)
            sourceSets["jsNodeTest"].dependsOn(this)
        }

        val nativeMain by creating {
            dependsOn(commonMain)
        }

        val nativeTest by creating {
            dependsOn(commonTest)
        }

        //FIXME: Linux binaries will not compile due to the ld.gold linker being used. Windows cannot use elf objects.
//        val linuxMain by creating {
//            dependsOn(nativeMain)
//            linuxTargets.forEach { sourceSets["${it}Main"].dependsOn(this) }
//            dependencies {
//                implementation("io.ktor:ktor-client-curl:$ktorVersion")
//            }
//        }
//
//        val linuxTest by creating {
//            dependsOn(linuxMain)
//            linuxTargets.forEach { sourceSets["${it}Test"].dependsOn(this) }
//        }

        val darwinMain by creating {
            dependsOn(nativeMain)
            darwinTargets.forEach { sourceSets["${it}Main"].dependsOn(this) }
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }

        val darwinTest by creating {
            dependsOn(nativeTest)
            darwinTargets.forEach { sourceSets["${it}Test"].dependsOn(this) }

        }

        val mingwMain by creating {
            dependsOn(nativeMain)
            mingwTargets.forEach { sourceSets["${it}Main"].dependsOn(this) }
            dependencies {
                implementation("io.ktor:ktor-client-winhttp:$ktorVersion")
            }

        }

        val mingwTest by creating {
            dependsOn(nativeTest)
            mingwTargets.forEach { sourceSets["${it}Test"].dependsOn(this) }
        }

        val androidMain by getting {
            dependsOn(nativeMain)
        }

        val androidUnitTest by getting {
            dependsOn(nativeTest)
        }

        val androidInstrumentedTest by getting {
            dependsOn(nativeTest)
        }

    }
}
