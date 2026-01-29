package com.example.book_store.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.book_store.presentation.auth.onboarding.OnboardingScreen
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.presentation.auth.login.LoginScreen
import com.example.book_store.presentation.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    userPreferences: UserPreferences
) {
    composable(Screen.Onboarding.route) {
        OnboardingScreen(
            navController = navController,
            userPreferences = userPreferences
        )
    }

    composable(Screen.Register.route) {
        RegisterScreen(navController,userPreferences)
    }
    composable(Screen.Login.route) {
        LoginScreen(navController,userPreferences, TokenStore)
    }
}
