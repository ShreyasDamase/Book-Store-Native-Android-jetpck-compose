package com.example.book_store.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.book_store.presentation.auth.onboarding.OnboardingScreen
import com.example.book_store.presentation.auth.login.LoginScreen
import com.example.book_store.presentation.auth.login.OtpScreen
import com.example.book_store.presentation.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,

    ) {
    navigation(
        startDestination = Screen.Onboarding.route,

        route = AUTH_TOUTE,

        ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                navController = navController,
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.Otp.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->

            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpScreen(navController, email)
        }
    }
}
