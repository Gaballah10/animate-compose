package com.example.animatedbottomsheet.navigation

import kotlinx.serialization.Serializable

sealed class Page {

    @Serializable
    data object Intro : Page()

    @Serializable
    data object Login : Page()

}

sealed class Graph {
    @Serializable
    data object Main : Graph()
}

fun Page.route(): String? {
    return this.javaClass.canonicalName
}