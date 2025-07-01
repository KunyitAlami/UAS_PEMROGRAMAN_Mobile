package com.example.uas_mobile.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        icon = Icons.Default.Home,
        screen = Screen.Home
    ),
    BottomNavItem(
        title = "Form",
        icon = Icons.Default.Description,
        screen = Screen.DiabetesPrediction
    ),
    BottomNavItem(
        title = "Artikel",
        icon = Icons.Default.Article,
        screen = Screen.Article
    ),
    BottomNavItem(
        title = "Parameter",
        icon = Icons.Default.Settings,
        screen = Screen.Parameter
    ),
    BottomNavItem(
        title = "About",
        icon = Icons.Default.Info,
        screen = Screen.About
    )
)