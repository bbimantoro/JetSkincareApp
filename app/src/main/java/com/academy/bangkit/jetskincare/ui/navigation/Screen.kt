package com.academy.bangkit.jetskincare.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")

    object DetailSkincare : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
}