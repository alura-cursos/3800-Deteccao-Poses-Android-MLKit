package com.alura.mexeai.ui.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alura.mexeai.ui.screens.camera.AnalyzeScreen

internal const val home = "home"
internal const val cameraRoute = "camera"

@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onHideNavBar: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = home,
        modifier = modifier,
        enterTransition = { fadeIn(tween(200)) },
        exitTransition = { fadeOut(tween(200)) },
    ) {
        composable(home) {
            HomeScreen {
                navController.navigate(cameraRoute)
                onHideNavBar()
            }
        }

        composable(cameraRoute) {
            AnalyzeScreen()
        }
    }
}