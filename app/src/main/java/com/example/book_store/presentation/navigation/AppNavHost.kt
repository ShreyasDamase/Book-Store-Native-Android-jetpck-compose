package com.example.book_store.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.presentation.screens.HomeScreen
import com.example.book_store.presentation.screens.SplashScreen

@Composable
fun AppNavHost(
    userPreferences: UserPreferences,tokenStore: TokenStore
){
    val navController= rememberNavController()
    NavHost(
        navController=navController
        ,startDestination= Screen.Splash.route
    ){

        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(  navController = navController,
                userPreferences = userPreferences,
                tokenStore = tokenStore)
        }
        authNavGraph(
            navController =navController,
            userPreferences=userPreferences
        )
        composable(Screen.Home.route) {
            HomeScreen(navController = navController ,userPreferences=userPreferences,    tokenStore
            )
        }

    }
}