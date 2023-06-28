package com.academy.bangkit.jetskincare

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
                HomeScreen(navigateToDetail = { skincareId ->
                    navController.navigate(Screen.DetailSkincare.createRoute(skincareId))
                })
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(
                    onOrderButtonClicked = { message ->
                        shareOrder(context, message)
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = Screen.DetailSkincare.route,
                arguments = listOf(navArgument("skincareId") {
                    type = NavType.LongType
                }),
            ) {
                val id = it.arguments?.getLong("skincareId") ?: -1L
                DetailScreen(
                    skincareId = id,
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
                    }
                )
            }
        }
    }
}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.skincare_order))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(Intent.createChooser(intent, context.getString(R.string.skincare_order)))
}

