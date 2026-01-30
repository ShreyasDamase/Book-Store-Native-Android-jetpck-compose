package com.example.book_store.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.presentation.screens.HomeScreen
import com.example.book_store.presentation.auth.onboarding.SplashScreen

@Composable
fun AppNavHost(

) {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.Splash.route
    ) {

        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(
                navController = navController,
            )
        }
        authNavGraph(
            navController = navController,
        )
        homeNavGraph(
            navController = navController
        )

    }
}