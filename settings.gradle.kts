pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven ( url ="https://dl.cloudsmith.io/public/cometchat/cometchat/maven/" )

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url ="https://dl.cloudsmith.io/public/cometchat/cometchat/maven/" )

    }
}

rootProject.name = "Chatgram"
include(":app")
