package com.example.animatedbottomsheet.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
 import com.example.animatedbottomsheet.navigation.Graph
import com.example.animatedbottomsheet.navigation.Page
import com.example.animatedbottomsheet.ui.intro.screen.IntroPage
import com.example.animatedbottomsheet.ui.intro.viewmodel.IntroViewModel
import com.example.animatedbottomsheet.ui.login.screen.LoginPage
import com.example.animatedbottomsheet.utils.composableHorizontalSlide


@Composable
fun MainGraph(
    mainNavController: NavHostController,
) {
    NavHost(
        navController = mainNavController,
        startDestination = Page.Intro,
        route = Graph.Main::class
    ) {

        composableHorizontalSlide<Page.Intro> {
            val viewModel = hiltViewModel<IntroViewModel>()
            IntroPage(
                viewModel,
                mainRouter = MainRouter(mainNavController)
            )
        }

        composableHorizontalSlide<Page.Login> {
            LoginPage()
        }

    }
}