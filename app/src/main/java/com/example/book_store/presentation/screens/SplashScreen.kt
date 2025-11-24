package com.example.book_store.presentation.screens

import AnimatedPreLoader
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
 import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    // Wait for 2 seconds, then call onTimeout
    LaunchedEffect(true) {
        delay(2000)  // 2 seconds
        onTimeout()
    }

    // Center the animation on screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedPreLoader()
    }
}
