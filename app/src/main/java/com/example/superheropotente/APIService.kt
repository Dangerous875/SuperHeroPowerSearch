package com.example.superheropotente

import com.example.superheropotente.data.SuperHeroDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("3080941948705073/search/{name}")
    suspend fun getHeroByName(@Path("name") superHeroName : String):Response<SuperHeroDataResponse>
}