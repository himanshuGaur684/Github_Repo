package com.example.githubrepo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.githubrepo.presentation.add_repo.AddRepoScreen
import com.example.githubrepo.presentation.add_repo.AddRepoViewModel
import com.example.githubrepo.presentation.landing.LandingScreen
import com.example.githubrepo.presentation.landing.LandingViewModel

enum class NavDestination(val route: String) {
    LANDING("landing"),
    ADD("add")
}


@Composable
fun MainNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController, startDestination = NavDestination.LANDING.route
    ) {
        composable(NavDestination.LANDING.route) {
            val viewModel = hiltViewModel<LandingViewModel>()
            LandingScreen(viewModel = viewModel, navHostController)
        }
        composable(NavDestination.ADD.route) {
            val viewModel = hiltViewModel<AddRepoViewModel>()
            AddRepoScreen(viewModel = viewModel, navController = navHostController)
        }
    }
}
