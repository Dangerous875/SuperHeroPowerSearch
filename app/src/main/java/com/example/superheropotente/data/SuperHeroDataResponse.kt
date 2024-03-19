package com.example.superheropotente.data

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val isSuccess: String,
    @SerializedName("results") val superheroes: List<SuperHeroItem>
)
