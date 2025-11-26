package com.example.book_store.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    userPreferences: UserPreferences,
    tokenStore: TokenStore
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Welcome Home!", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    scope.launch {
                        // 1) Clear secure tokens
                        tokenStore.clearTokens()

                        // 2) Mark logged out
                        userPreferences.setLoggedIn(false)

                        // 3) Navigate out
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                }
            ) {
                Text("Erase Token & Logout")
            }
        }
    }
}
