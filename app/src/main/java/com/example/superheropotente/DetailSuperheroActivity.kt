package com.example.superheropotente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.bumptech.glide.Glide
import com.example.superheropotente.data.SuperHeroItem
import com.example.superheropotente.data.SuperHeroPowerStats
import com.example.superheropotente.databinding.ActivityDetailSuperheroBinding
import com.example.superheropotente.service.SuperHero
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailSuperheroBinding
    private lateinit var superHero : SuperHeroItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        superHero = SuperHero.getSuperHero(intent.getStringExtra("IDHero")!!)
        Log.i("asdasd",superHero.toString())
        initUI()
    }

    private fun initUI() {
        chargeImage()
        binding.tvSuperheroName.text = superHero.name
        if (superHero.powerstats!=null){
            prepareStats(superHero.powerstats)

        }
        binding.tvSuperheroName.text = superHero.biography.fullName
        binding.tvPublisher.text = superHero.biography.publisher
        binding.alignment.text = "Alineación = ${superHero.biography.alignment}"
        binding.firstApp.text = "Primera aparicion = ${superHero.biography.firstAppearance}"
        binding.gender.text = "Género = ${superHero.appearance.gender}"
        binding.race.text = "Raza = ${superHero.appearance.race}"
    }

//    private fun prepareStats(powerstats: SuperHeroPowerStats) {
//
//            updateHeight(binding.viewCombat, powerstats.combat )
//            updateHeight(binding.viewDurability, powerstats.durability)
//            updateHeight(binding.viewSpeed, powerstats.speed)
//            updateHeight(binding.viewStrength, powerstats.strength)
//            updateHeight(binding.viewIntelligence, powerstats.intelligence)
//            updateHeight(binding.viewPower, powerstats.power)
//
//    }

    private fun prepareStats(powerstats: SuperHeroPowerStats?) {
        powerstats?.let {
            updateHeight(binding.viewCombat, it.combat)
            updateHeight(binding.viewDurability, it.durability)
            updateHeight(binding.viewSpeed, it.speed)
            updateHeight(binding.viewStrength, it.strength)
            updateHeight(binding.viewIntelligence, it.intelligence)
            updateHeight(binding.viewPower, it.power)
        }
    }

    private fun updateHeight(view: View, stat:String?){
        var defaultStat = "50.0"
        if (stat != null){
            defaultStat = stat
        }
        val params = view.layoutParams
        params.height = pxToDp(defaultStat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun chargeImage() {
        Glide.with(this)
            .load(superHero.image.url)
            .into(binding.ivSuperhero)
    }
}