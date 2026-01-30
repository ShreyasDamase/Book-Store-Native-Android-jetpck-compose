package com.example.book_store.presentation.navigation

const val HOME_ROUTE = "homeRoute"
const val AUTH_TOUTE = "authRoute"

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Otp : Screen("otp/{email}") {
        fun createRoute(email: String) = "otp/$email"
    }

    // Bottom tab container
    object BottomBar : Screen("bottomBar")
    // Tabs

    object Home : Screen("home")

    object Feed : Screen("feed")
    object AddBook : Screen("add_book")
    object Profile : Screen("profile")

}