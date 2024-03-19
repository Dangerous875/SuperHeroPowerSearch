package com.example.superheropotente.data

data class SuperHeroItem(
    val id: String,
    val name: String,
    val powerstats: SuperHeroPowerStats,
    val biography : SuperHeroBiography,
    val appearance : SuperHeroAppearance,
    val image : SuperHeroImage
)
