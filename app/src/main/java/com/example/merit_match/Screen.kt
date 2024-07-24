package com.example.merit_match

sealed class Screen(val route: String) {
    data object LoginPage : Screen("login_page")
    data object HomePage : Screen("home_page")
    data object CreatePage: Screen("create_page")
}