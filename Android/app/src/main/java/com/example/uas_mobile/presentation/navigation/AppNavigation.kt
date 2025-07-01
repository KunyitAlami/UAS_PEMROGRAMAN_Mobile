package com.example.uas_mobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.uas_mobile.presentation.ui.screens.about.AboutScreen
import com.example.uas_mobile.presentation.ui.screens.article.ArticleDetailScreen
import com.example.uas_mobile.presentation.ui.screens.article.ArticleScreen
import com.example.uas_mobile.presentation.ui.screens.parameter.ParameterScreen
import com.example.uas_mobile.presentation.ui.DiabetesPredictionScreen
import com.example.uas_mobile.presentation.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToArticle = { navController.navigate(Screen.Article.route) },
                onNavigateToParameter = { navController.navigate(Screen.Parameter.route) },
                onNavigateToAbout = { navController.navigate(Screen.About.route) },
                onArticleClick = { articleId ->
                    navController.navigate(Screen.ArticleDetail.createRoute(articleId))
                },
                onNavigateToPrediction = { navController.navigate(Screen.DiabetesPrediction.route) }

            )
        }

        composable(Screen.Article.route) {
            ArticleScreen(
                onArticleClick = { articleId ->
                    navController.navigate(
                        Screen.ArticleDetail.createRoute(articleId)
                    )
                }
            )
        }

        composable(
            route = Screen.ArticleDetail.route,
            arguments = listOf(
                navArgument("articleId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getInt("articleId") ?: 0
            ArticleDetailScreen(
                articleId = articleId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Parameter.route) {
            ParameterScreen()
        }

        composable(Screen.About.route) {
            AboutScreen()
        }
        composable(Screen.DiabetesPrediction.route) {
            DiabetesPredictionScreen()
        }

    }
}