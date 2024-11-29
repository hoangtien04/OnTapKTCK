package com.example.ontapcuoiky

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


sealed class Screen(val route:String){
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen")
}
@Composable
fun NavGraph(navController: NavHostController,viewModel: ThucDonViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            homeScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.Detail.route + "?id={id}",
            arguments = listOf(navArgument("id") {nullable = true}
            )
        ) {
            var id = it.arguments?.getString("id")
            if(id != null){
                DetailScreen(navController = navController, id, viewModel)
            }

        }


    }
}