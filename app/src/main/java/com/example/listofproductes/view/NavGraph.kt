package com.example.listofproductes.view

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.listofproductes.viewmodel.ProductViewModel


@Composable
fun NavGraph(navHostController: NavHostController, startDestination: String) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        Modifier.fillMaxSize(),enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(200)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(200)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(200)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(200)
            )
        }
    ) {



        composable(route = NavRote.UserListScreen.path) {
            val mainViewmodel = hiltViewModel<ProductViewModel>()

            UserListScreen(navHostController,mainViewmodel)
        }




    }
}
