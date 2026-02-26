package com.github.lexya06.culinaryguide.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.lexya06.culinaryguide.ui.adduser.AddUserScreen
import com.github.lexya06.culinaryguide.ui.edituser.EditUserScreen
import com.github.lexya06.culinaryguide.ui.main.MainScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {

        composable(Screen.Main.route) {
            MainScreen(
                onAddClick = {
                    navController.navigate(Screen.AddUser.route)
                },
                onEditClick = { userId ->
                    navController.navigate(
                        Screen.EditUser.createRoute(userId)
                    )
                }
            )
        }

        composable(Screen.AddUser.route) {
            AddUserScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.EditUser.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->

            val userId: Long =
                backStackEntry.arguments?.getLong("userId") ?: 0

            EditUserScreen(
                userId = userId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}