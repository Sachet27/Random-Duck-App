package com.example.randomduckexperiment

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun DuckScreen(
    modifier: Modifier= Modifier
){
    val duckViewModel: DuckViewModel = viewModel()
    val viewState by duckViewModel.imageState
    Box(
        modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier=Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when{
                viewState.loading-> {
                    CircularProgressIndicator()
                }
                viewState.error!=null -> {
                    Text(
                        text = "${viewState.error}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                else->{
                    DuckLoaded(viewState.imageURL, duckViewModel)
                }
            }

        }
    }
}

@Composable
fun DuckLoaded(
    imageURL: String,
    duckViewModel: DuckViewModel
){
    Text(
        text= "Random Duck",
        fontSize= 20.sp,
        fontWeight = FontWeight.Bold
    )
    Image(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))
            .border(width = 2.dp, color = Color.LightGray),
        painter= rememberAsyncImagePainter(
            model= imageURL
        ),
        contentDescription = "Duck",
        contentScale = ContentScale.Crop
    )
    Button(
        onClick={
            duckViewModel.fetchDuckImage()
        }
    ){
        Text("Next duck")
    }
}