// settings.gradle.kts

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io") // Added JitPack repository for external libraries
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Google's Maven repository for Android dependencies
        mavenCentral() // Central repository for general dependencies
        maven("https://jitpack.io") // JitPack repository
    }
}

// Set the root project name and include app module
rootProject.name = "Project"
include(":app")
