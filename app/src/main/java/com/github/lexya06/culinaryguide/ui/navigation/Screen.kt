package com.github.lexya06.culinaryguide.ui.navigation

sealed class Screen(val route: String) {

    object Main : Screen("main")

    object AddUser : Screen("add_user")

    object EditUser : Screen("edit_user/{userId}") {
        fun createRoute(userId: Long): String {
            return "edit_user/$userId"
        }
    }
}