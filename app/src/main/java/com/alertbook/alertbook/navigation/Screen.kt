package com.alertbook.alertbook.navigation

sealed class Screen(val route: String) {
    object Intro : Screen(route = "intro_screen")
    object DashBoard : Screen(route = "dashboard_screen")
}