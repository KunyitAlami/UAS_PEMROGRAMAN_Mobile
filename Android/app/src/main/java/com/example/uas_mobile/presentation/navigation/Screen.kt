package com.example.uas_mobile.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Article : Screen("article")
    object ArticleDetail : Screen("article_detail/{articleId}") {
        fun createRoute(articleId: Int) = "article_detail/$articleId"
    }
    object Parameter : Screen("parameter")
    object About : Screen("about")
    object DiabetesPrediction : Screen("prediction")
}