package com.example.apilist.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.apilist.model.Role
import com.example.apilist.model.ValorantAgentes
import com.example.apilist.navigation.Routes
import com.example.apilist.viewModel.MyViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(uuid: String, navController: NavController, myViewModel: MyViewModel) {
    // Obtener el personaje al iniciar la pantalla
    LaunchedEffect(key1 = uuid) {
        myViewModel.getCharacter(uuid)
    }

    // Observar el estado del personaje
    val agent: Data? by myViewModel.agent.observeAsState()

    // Verificar si el personaje no es nulo antes de mostrar los detalles
    if (agent != null) {
        Column {
            Text(text = "UUID: ${agent!!.uuid}")
            Text(text = "Nombre: ${agent!!.displayName}")
            // Aquí puedes agregar más detalles del personaje si lo necesitas
        }
    } else {
        // Mientras se carga la información, puedes mostrar un indicador de carga
        CircularProgressIndicator(modifier = Modifier.size(32.dp))
    }
}
