package com.example.apilist.navigation

sealed class Routes(val route: String){
    object SearchScreen: Routes("search_screen")
    object DetailScreen:Routes("detail_screen/{uuid}") {
        fun createRoute(uuid: String) = "detail_screen/$uuid"
    }
    object FavouritesScreen:Routes("favourites_screen")
}