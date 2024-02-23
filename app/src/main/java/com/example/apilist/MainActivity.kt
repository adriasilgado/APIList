package com.example.apilist


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apilist.navigation.Routes
import com.example.apilist.ui.theme.APIListTheme
import com.example.apilist.view.DetailScreen
import com.example.apilist.view.FavouritesScreen
import com.example.apilist.view.SearchScreen
import com.example.apilist.viewModel.MyViewModel

val valo = FontFamily(Font(R.font.valorant))
val arabic = FontFamily(Font(R.font.nextarabic))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val myViewModel by viewModels<MyViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            APIListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.SearchScreen.route) {
                        composable(Routes.SearchScreen.route) { SearchScreen(navigationController, myViewModel) }
                        composable(
                            Routes.DetailScreen.route,
                            arguments = listOf(
                                navArgument("uuid") {type = NavType.StringType})) {
                                backStackEntry ->
                            DetailScreen(
                                backStackEntry.arguments?.getString("uuid").orEmpty(),
                                navigationController, myViewModel
                            )
                        }
                        composable(Routes.FavouritesScreen.route) { FavouritesScreen(navigationController, myViewModel) }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APIListTheme {
    }
}