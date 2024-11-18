package com.sahil.actorsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahil.actorsapp.repository.CharacterRepo
import com.sahil.actorsapp.retrofit.Character
import com.sahil.actorsapp.retrofit.RetrofitInstance
import com.sahil.actorsapp.ui.theme.ActorsAppTheme
import com.sahil.actorsapp.viewModel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActorsAppTheme {
                val characterAPI = RetrofitInstance.provideAPI(RetrofitInstance.provideRetrofit())
                val characterRepo = CharacterRepo(characterAPI)
                val viewModel = CharacterViewModel(characterRepo)
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: CharacterViewModel) {
    val characterz by viewModel.state.collectAsState()
    val nonEmptyList = mutableListOf<Character>()
    characterz.forEach{
        if(it.image != "") {
            nonEmptyList.add(it)
        }
    }
    ActorList(characterList = nonEmptyList)
}

@Composable
fun ActorList(characterList: List<Character>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.padding(8.dp)) {
        items(items = characterList) {
            item -> CardItem(item)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItem(character: Character) {
    Column {
        GlideImage(model = character.image,
            modifier = Modifier.padding(6.dp).size(width = 100.dp, height = 140.dp),
            contentDescription = "Image")
        Text(character.actor, fontSize = 24.sp)
    }
}

