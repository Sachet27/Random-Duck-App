package com.example.randomduckexperiment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val retrofit:Retrofit= Retrofit.Builder()
    .baseUrl("https://random-d.uk/api/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface APIService{
@GET("random")
suspend fun getImageURL(): RandomDuckURL
}