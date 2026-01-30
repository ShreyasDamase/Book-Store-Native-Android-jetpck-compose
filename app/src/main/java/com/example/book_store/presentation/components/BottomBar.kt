package com.example.book_store.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.book_store.presentation.navigation.Screen

@Composable
fun BottomBar(navController: NavHostController) {

    val items = listOf(
        Screen.Feed,
        Screen.AddBook,
        Screen.Profile
    )

    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Add,
        Icons.Default.Person
    )

    NavigationBar() {

        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEachIndexed { index, screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(screen.route) },
                icon = { Icon(icons[index], null) }
            )
        }
    }
}
