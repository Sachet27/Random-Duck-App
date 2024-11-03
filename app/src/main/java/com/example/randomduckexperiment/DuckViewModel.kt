package com.example.randomduckexperiment

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Error

class DuckViewModel:ViewModel() {
    init{
        fetchDuckImage()
    }
    private val _imageState= mutableStateOf(ImageState())
    val imageState: State<ImageState> = _imageState

      fun fetchDuckImage(){
        val DuckService= retrofit.create(APIService::class.java)
        viewModelScope.launch {
            try{
                val response= DuckService.getImageURL()
                _imageState.value= _imageState.value.copy(
                    loading = false,
                    error= null,
                    imageURL= response.url
                )
            }
            catch(e:Exception){
                Log.e("DuckViewModel", "Error fetching duck image: ${e.message}", e)
                _imageState.value= _imageState.value.copy(
                    loading=false,
                    error= "Failed to load image. ${e.message}")
            }
        }
    }
}

data class ImageState(
        val loading: Boolean= true,
        val error: String?= null,
        val imageURL: String= ""
        )