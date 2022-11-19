buildscript {
    val agp_version by extra("7.3.0")
    val compose_version by extra { "1.2.0" }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$agp_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}