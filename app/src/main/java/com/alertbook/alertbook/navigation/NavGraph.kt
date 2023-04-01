package com.alertbook.alertbook.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alertbook.alertbook.screen.DashBoard
import com.alertbook.alertbook.screen.IntroScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Intro.route) {
            IntroScreen(navController = navController)
        }
        composable(route = Screen.DashBoard.route) {
            DashBoard()
        }
    }
}