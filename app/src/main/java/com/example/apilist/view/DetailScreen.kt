package com.example.apilist.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist.R
import com.example.apilist.arabic
import com.example.apilist.model.Agente
import com.example.apilist.model.Data
import com.example.apilist.navigation.BottomNavigation
import com.example.apilist.valo
import com.example.apilist.viewModel.MyViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(uuid: String, navController: NavController, myViewModel: MyViewModel) {
    myViewModel.getCharacter(uuid)
    val agent: Agente? by myViewModel.agent.observeAsState()
    if (agent != null) {
        Scaffold(topBar = { MyTopAppBarDetail(myViewModel, agent) },bottomBar = { MyBottomBarDetail(navController) }) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)) {
                if (agent != null) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .background(Color(33, 48, 79))
                        .fillMaxSize()
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxHeight(0.4f)
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            GlideImage(
                                model = agent!!.data.background,
                                contentDescription = "Name",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            GlideImage(
                                model = agent!!.data.fullPortrait,
                                contentDescription = "Agent",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Text(
                            text = agent!!.data.displayName,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            fontFamily = valo,
                            modifier = Modifier.padding(top = 4.dp),
                            fontSize = 30.sp
                        )
                        Text(
                            text = agent!!.data.description,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            fontFamily = arabic,
                            fontSize = 17.sp,
                        )
                        val state = rememberLazyListState()
                        LazyRow (state = state, flingBehavior = rememberSnapFlingBehavior(lazyListState = state)){
                            items(4) {habilidad ->
                                Card(
                                    colors = CardDefaults.cardColors(Color.Transparent),
                                    border = BorderStroke(2.dp, Color.LightGray),
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(380.dp)
                                        .padding(1.dp))
                                {
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        LazyColumn(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) { items(1) {
                                            GlideImage(
                                                model = agent!!.data.abilities[habilidad].displayIcon,
                                                contentDescription = "Icon Ability",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .fillMaxHeight(0.4f)
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 142.dp)
                                            )
                                            Text(
                                                text = agent!!.data.abilities[habilidad].displayName,
                                                style = MaterialTheme.typography.bodyLarge,
                                                textAlign = TextAlign.Center,
                                                fontFamily = valo,
                                                modifier = Modifier.padding(top = 4.dp),
                                                fontSize = 20.sp)
                                            Text(
                                                text = agent!!.data.abilities[habilidad].description,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 6.dp),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                }
            }
        }
    }
    else {
        CircularProgressIndicator(modifier = Modifier.size(32.dp))
    }
}

@Composable
fun MyTopAppBarDetail(myViewModel: MyViewModel, agent:Agente?) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val esFav:Boolean by myViewModel.isFavourite.observeAsState(false)
    var agente = Data(agent!!.data.abilities,agent!!.data.background, agent!!.data.description, agent!!.data.displayIcon,
        agent!!.data.displayName, agent!!.data.fullPortrait, agent!!.data.isPlayableCharacter, agent!!.data.role, agent!!.data.uuid)
    if (agente != null)
    myViewModel.isFavorite(agente)
    TopAppBar(
        title = {  },
        backgroundColor = Color(222,48,79),
        contentColor = Color.White,
        elevation = AppBarDefaults.TopAppBarElevation,
        navigationIcon = {
            IconButton(onClick = { dispatcher?.onBackPressed() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = {
                myViewModel.changeFavourite()
                if (esFav) myViewModel.saveAsFavourite(agente)
                else myViewModel.deleteFavourite(agente)
            }) {
                Image(painter = painterResource(id = if (esFav) R.drawable.logoentero else R.drawable.logoborde),
                    contentDescription = "Like",
                    colorFilter = ColorFilter.tint(Color.White))
            }
        },
        modifier = Modifier.statusBarsPadding()
    )
}

@Composable
fun MyBottomBarDetail(navController: NavController) {
    val bottomIcons = listOf(BottomNavigation.Home)
    BottomNavigation(backgroundColor = Color(222,48,79)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomIcons.forEach{
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
