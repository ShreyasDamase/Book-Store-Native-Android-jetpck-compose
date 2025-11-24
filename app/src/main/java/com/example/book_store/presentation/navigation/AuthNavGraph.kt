package com.example.book_store.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.book_store.presentation.onboarding.OnboardingScreen
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.presentation.screens.RegisterScreen

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
        RegisterScreen()
    }
}
