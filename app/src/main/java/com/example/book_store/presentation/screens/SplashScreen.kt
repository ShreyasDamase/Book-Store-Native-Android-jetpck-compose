package com.example.book_store.presentation.screens

import AnimatedPreLoader
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.presentation.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(navController: NavController ,userPreferences: UserPreferences ) {

    // Wait for 2 seconds, then call onTimeout
    LaunchedEffect(true) {
        delay(2000)  // 2 seconds
        val onboardingDone = userPreferences.onboardingCompleteFlow.first()
if(onboardingDone){
    navController.navigate(Screen.Register.route){
        popUpTo(Screen.Onboarding.route){inclusive=true}
    }
} else {
    navController.navigate(Screen.Onboarding.route){
        popUpTo(Screen.Splash.route){inclusive=true}
    }
}
      }

    // Center the animation on screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedPreLoader()
    }
}
