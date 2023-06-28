package com.academy.bangkit.jetskincare

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.academy.bangkit.jetskincare.ui.components.BottomBar
import com.academy.bangkit.jetskincare.ui.navigation.Screen
import com.academy.bangkit.jetskincare.ui.screen.cart.CartScreen
import com.academy.bangkit.jetskincare.ui.screen.detail.DetailScreen
import com.academy.bangkit.jetskincare.ui.screen.home.HomeScreen
import com.academy.bangkit.jetskincare.ui.screen.profil.ProfileScreen

@Composable
fun JetSkincareApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailSkincare.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { id ->
                    navController.navigate(Screen.DetailSkincare.createRoute(id))
                })
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = Screen.DetailSkincare.route,
                arguments = listOf(navArgument("id") {
                    type = NavType.IntType
                }),
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                DetailScreen(
                    id = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

