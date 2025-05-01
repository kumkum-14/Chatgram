pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven ( url ="https://dl.cloudsmith.io/public/cometchat/cometchat/maven/" )  // Add CometChat repository here
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url ="https://dl.cloudsmith.io/public/cometchat/cometchat/maven/" )  // Add CometChat repository here too
    }
}

rootProject.name = "Chatgram"
include(":app")
