package com.academy.bangkit.jetskincare.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")

    object DetailSkincare : Screen("home/{skincareId}") {
        fun createRoute(skincareId: Long) = "home/$skincareId"
    }
}