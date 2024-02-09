package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist.R
import com.example.apilist.model.Data
import com.example.apilist.model.ValorantAgentes
import com.example.apilist.navigation.Routes
import com.example.apilist.viewModel.MyViewModel
@Composable
fun SearchScreen(navController: NavController, myViewModel: MyViewModel) {
    MyRecyclerView(myViewModel)
}

@Composable
fun MyRecyclerView(myViewModel: MyViewModel) {
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
                CharacterItem(characters.data[it])
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: Data) {
    if (character.isPlayableCharacter) {
        Card(
            border = BorderStroke(2.dp, Color.LightGray),
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp)
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
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}


