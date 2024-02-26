package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialogDefaults.titleContentColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist.R
import com.example.apilist.model.Data
import com.example.apilist.model.ValorantAgentes
import com.example.apilist.navigation.BottomNavigation
import com.example.apilist.navigation.Routes
import com.example.apilist.valo
import com.example.apilist.viewModel.MyViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, myViewModel: MyViewModel) {
    Scaffold(topBar = { MyTopAppBar(myViewModel)},bottomBar = { MyBottomBar(navController)}) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
            MyRecyclerView(navController, myViewModel)
        }
    }
}

@Composable
fun MyRecyclerView(navController: NavController, myViewModel: MyViewModel) {
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val characters: ValorantAgentes by myViewModel.characters.observeAsState(ValorantAgentes(emptyList(), 0))
    myViewModel.getCharacters()
    if(showLoading){
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = Color.Black
        )
    }
    else{
        LazyColumn() {
            items(characters.data.size) {
                CharacterItem(characters.data[it], navController)
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: Data, navController: NavController) {
    if (character.isPlayableCharacter) {
        Card(
            border = BorderStroke(2.dp, Color.LightGray),
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp)
                .clickable { navController.navigate(Routes.DetailScreen.createRoute(character.uuid)) }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.fondofin),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    GlideImage(
                        model = character.displayIcon,
                        contentDescription = "Character Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = character.displayName,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontFamily = valo
                    )
                }
            }
        }
    }
}


@Composable
fun MyTopAppBar(myViewModel: MyViewModel) {
    var esconder by remember { mutableStateOf(true) }
    TopAppBar(
        title = {  },
        backgroundColor = Color(222,48,79),
        contentColor = Color.White,
        elevation = AppBarDefaults.TopAppBarElevation,
        actions = {
            if (!esconder) {
                MySearchBar(myViewModel)
            }
            IconButton(onClick = { esconder = !esconder }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        modifier = Modifier.statusBarsPadding()
    )
}

@Composable
fun MyBottomBar(navController: NavController) {
    val bottomIcons = listOf(BottomNavigation.Home, BottomNavigation.Favourites)
    BottomNavigation(backgroundColor = Color(222,48,79)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomIcons.forEach{
            println("RUTA: $currentRoute")
            BottomNavigationItem(
                icon = { Icon(it.icon, contentDescription = it.label) },
                label = { Text(it.label, fontFamily = valo, color = Color.Black)},
                selected = currentRoute == it.route,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.White,
                alwaysShowLabel = currentRoute == it.route,
                onClick = {
                    if (currentRoute != it.route) {
                        navController.navigate(it.route)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar (myViewModel: MyViewModel) {
    val searchText by myViewModel.searchText.observeAsState("")
    SearchBar(
        query = searchText,
        onQueryChange = { myViewModel.onSearchTextChange(it) },
        onSearch = { myViewModel.onSearchTextChange(it) },
        active = true,
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")},
        placeholder = { Text("What are you looking for?") },
        onActiveChange = {}, modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth(0.85f)
            .clip(CircleShape)) {
    }
}


