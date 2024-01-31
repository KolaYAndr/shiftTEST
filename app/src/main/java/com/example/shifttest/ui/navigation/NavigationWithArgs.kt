package com.example.shifttest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shifttest.ui.screens.user_detail.DetailScreen
import com.example.shifttest.ui.screens.users_list.UsersListScreen
import com.example.shifttest.ui.view_model.UsersViewModel


@Composable
fun NavigationWithUsers() {
    val navController = rememberNavController()
    val viewModel = viewModel<UsersViewModel>()
    NavHost(navController = navController, startDestination = USERS_LIST_SCREEN) {
        composable(USERS_LIST_SCREEN) {
            UsersListScreen(viewModel) {
                navController.navigate("$DETAIL_SCREEN/${it}")
            }
        }
        composable(
            route = "$DETAIL_SCREEN/{$USERNAME}",
            arguments = listOf(
                navArgument(USERNAME) {
                    type = NavType.StringType
                }
            )
        ) {
            val userName = it.arguments?.getString(USERNAME) ?: ""
            DetailScreen(viewModel = viewModel, userName = userName) {
                navController.navigateUp()
            }
        }
    }
}

private const val USERS_LIST_SCREEN = "users_list_screen"
private const val DETAIL_SCREEN = "detail_screen"
private const val USERNAME = "user_name"