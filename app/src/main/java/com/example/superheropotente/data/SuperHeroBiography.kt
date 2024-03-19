package com.example.superheropotente.data

import com.google.gson.annotations.SerializedName

data class SuperHeroBiography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("first-appearance") val firstAppearance: String,
    val publisher: String,
    val alignment: String
)
