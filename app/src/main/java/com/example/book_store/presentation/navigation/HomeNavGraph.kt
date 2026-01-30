package com.example.book_store.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.book_store.presentation.components.BottomBar


fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.BottomBar.route,
        route = HOME_ROUTE
    ) {
        composable(Screen.BottomBar.route) {
            BottomBar()
        }
    }
}