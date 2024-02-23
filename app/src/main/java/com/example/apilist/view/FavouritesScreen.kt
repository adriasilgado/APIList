package com.example.apilist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apilist.model.Data
import com.example.apilist.model.ValorantAgentes
import com.example.apilist.viewModel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(navController: NavController, myViewModel: MyViewModel) {
    Scaffold(topBar = { MyTopAppBar()},bottomBar = { MyBottomBar(navController)}) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
            val showLoading: Boolean by myViewModel.loading.observeAsState(true)
            val characters: MutableList<Data> by myViewModel.favourites.observeAsState(mutableListOf())
            myViewModel.getFavourites()
            if(showLoading){
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = Color.Black
                )
            }
            else{
                LazyColumn() {
                    items(characters.size) {
                        CharacterItem(characters[it], navController)
                    }
                }
            }
        }
    }
}

fun MyTopAppBarFav(){

}