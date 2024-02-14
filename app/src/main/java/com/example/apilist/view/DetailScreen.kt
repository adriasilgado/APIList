package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist.R
import com.example.apilist.arabic
import com.example.apilist.model.Agente
import com.example.apilist.model.Data
import com.example.apilist.model.Role
import com.example.apilist.model.ValorantAgentes
import com.example.apilist.navigation.Routes
import com.example.apilist.valo
import com.example.apilist.viewModel.MyViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(uuid: String, navController: NavController, myViewModel: MyViewModel) {
    myViewModel.getCharacter(uuid)
    val agent: Agente? by myViewModel.agent.observeAsState()

    if (agent != null) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
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
                fontSize = 18.sp
            )
            val state = rememberLazyListState()
            LazyRow (state = state, flingBehavior = rememberSnapFlingBehavior(lazyListState = state)){
                items(4) {
                    Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        GlideImage(
                            model = agent!!.data.abilities[it].displayIcon,
                            contentDescription = "Icon Ability",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight(0.4f)
                                .fillMaxWidth()
                                .padding(horizontal = 142.dp)
                        )
                        Text(
                            text = agent!!.data.abilities[it].displayName,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            fontFamily = valo,
                            modifier = Modifier.padding(top = 4.dp),
                            fontSize = 20.sp)
                    }
                }
            }
        }
    } else {
        CircularProgressIndicator(modifier = Modifier.size(32.dp))
    }
}

