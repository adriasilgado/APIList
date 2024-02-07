package com.example.apilist.navigation

sealed class Routes(val route: String){
    object SearchScreen: Routes("search_screen")
}