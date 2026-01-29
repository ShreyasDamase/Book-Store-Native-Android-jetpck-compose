package com.example.book_store.presentation.auth.onboarding

import AnimatedPreLoader
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.presentation.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
@Composable
fun SplashScreen(
    navController: NavController,
    userPreferences: UserPreferences,
    tokenStore: TokenStore
) {
    LaunchedEffect(Unit) {
        delay(1500)

        val onboardingDone = userPreferences.onboardingCompleteFlow.first()
        val accessToken = tokenStore.getAccessToken()  // ← secure token

        when {
            !accessToken.isNullOrEmpty() -> {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }

            onboardingDone -> {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }

            else -> {
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedPreLoader()
    }
}
