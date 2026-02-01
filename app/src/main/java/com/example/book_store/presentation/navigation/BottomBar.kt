package com.example.book_store.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.book_store.presentation.components.BottomBar
import com.example.book_store.presentation.screens.CreateBookScreen

import com.example.book_store.presentation.screens.HomeScreen

@Composable
fun BottomBar(
    navController: NavHostController

) {

    val tabNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(tabNavController)
        }
    ) { padding ->

        NavHost(
            navController = tabNavController,
            startDestination = Screen.Feed.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(Screen.Feed.route) {
                HomeScreen(navController)
            }

            composable(Screen.AddBook.route) {
                CreateBookScreen()
            }

            composable(Screen.Profile.route) {
                Text("Profile Screen")
            }
        }
    }
}
