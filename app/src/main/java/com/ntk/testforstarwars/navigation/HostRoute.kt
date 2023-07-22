package com.ntk.testforstarwars.navigation

sealed class HostRoute(val route: String) {
    object Main: HostRoute("main_screen")
    object Favorite: HostRoute("favorite_screen")
    object Setting: HostRoute("setting_screen")
}