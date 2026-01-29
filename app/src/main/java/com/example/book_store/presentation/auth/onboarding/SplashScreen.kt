package com.example.book_store.presentation.auth.onboarding

import AnimatedPreLoader
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.book_store.presentation.navigation.Screen
import com.example.book_store.presentation.viewmodels.SessionViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(
    navController: NavController,

    ) {

    val sessionVM: SessionViewModel = hiltViewModel()

    val onboardingDone by sessionVM
        .userPreferences
        .onboardingCompleteFlow
        .collectAsState(initial = false)
    LaunchedEffect(onboardingDone) {
        delay(1500)

        val accessToken = sessionVM.tokenStore.getAccessToken()  // ← secure token

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
