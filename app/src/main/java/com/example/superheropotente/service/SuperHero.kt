package com.example.superheropotente.service

import com.example.superheropotente.data.SuperHeroItem
import java.util.Collections

class SuperHero {
    companion object{
        var listSuperHero : List<SuperHeroItem> = Collections.emptyList()

        fun getSuperHero(id:String):SuperHeroItem{
            return listSuperHero.find { it.id == id }!!
        }
    }
}