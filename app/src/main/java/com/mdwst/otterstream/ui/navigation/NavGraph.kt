package com.mdwst.otterstream.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdwst.otterstream.ui.screens.HomeScreen
import com.mdwst.otterstream.ui.screens.BrowseScreen
import com.mdwst.otterstream.ui.screens.SearchScreen
import com.mdwst.otterstream.ui.screens.PlayerScreen
import com.mdwst.otterstream.ui.screens.AddonManagerScreen
import com.mdwst.otterstream.ui.screens.SettingsScreen

sealed class NavRoute(val route: String) {
    object Home : NavRoute("home")
    object Browse : NavRoute("browse/{category}") {
        fun createRoute(category: String) = "browse/$category"
    }
    object Search : NavRoute("search")
    object Player : NavRoute("player/{metaId}/{type}") {
        fun createRoute(metaId: String, type: String) = "player/$metaId/$type"
    }
    object AddonManager : NavRoute("addons")
    object Settings : NavRoute("settings")
}

@Composable
fun OTTerStreamNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoute.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoute.Home.route) {
            HomeScreen(navController)
        }
        composable(NavRoute.Browse.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "movies"
            BrowseScreen(navController, category)
        }
        composable(NavRoute.Search.route) {
            SearchScreen(navController)
        }
        composable(NavRoute.Player.route) { backStackEntry ->
            val metaId = backStackEntry.arguments?.getString("metaId") ?: ""
            val type = backStackEntry.arguments?.getString("type") ?: ""
            PlayerScreen(navController, metaId, type)
        }
        composable(NavRoute.AddonManager.route) {
            AddonManagerScreen(navController)
        }
        composable(NavRoute.Settings.route) {
            SettingsScreen(navController)
        }
    }
}
