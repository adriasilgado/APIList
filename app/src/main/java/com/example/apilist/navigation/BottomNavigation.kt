package com.example.apilist.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigation(val route:String, val icon:ImageVector, val label:String) {
    object Home:BottomNavigation(Routes.SearchScreen.route, Icons.Filled.Home, "HoMe")
    object Favourites:BottomNavigation(Routes.DetailScreen.route, Icons.Filled.Star, "FaVourites")
}