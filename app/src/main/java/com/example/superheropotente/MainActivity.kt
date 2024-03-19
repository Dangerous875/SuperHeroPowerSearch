package com.example.superheropotente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheropotente.adapter.SuperHeroAdapter
import com.example.superheropotente.data.SuperHeroDataResponse
import com.example.superheropotente.data.SuperHeroItem
import com.example.superheropotente.databinding.ActivityMainBinding
import com.example.superheropotente.service.SuperHero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var superHeroAdapter : SuperHeroAdapter
    private val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {

        initRecyclerView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun initRecyclerView() {

        val onClickSuperHero = {superHero : SuperHeroItem ->
            val intent = Intent(this,DetailSuperheroActivity::class.java)
            intent.putExtra("IDHero",superHero.id)
            startActivity(intent)
        }
        superHeroAdapter = SuperHeroAdapter(SuperHero.listSuperHero,onClickSuperHero)
        binding.rvSuperhero.apply {
            adapter = superHeroAdapter
            layoutManager = linearLayoutManager
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        }
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val call = retrofit.create(APIService::class.java).getHeroByName(query)
            val response = call.body()
            Log.i("respuesta",response.toString())
                withContext(Dispatchers.Main){
                    binding.progressBar.isVisible = false
                    updateListSuperHero(call,response)
                }




        }
        hideKeyBoard()
    }

    private fun updateListSuperHero(
        call: Response<SuperHeroDataResponse>,
        response: SuperHeroDataResponse?
    ) {
        if (call.isSuccessful){
            SuperHero.listSuperHero = response?.superheroes ?: emptyList()
            var pos = 0
            for (superhero in SuperHero.listSuperHero){
                Log.i("respuesta","$pos .. ${superhero.toString()}")
                pos += 1
            }
            superHeroAdapter.updateSuperHeroList(SuperHero.listSuperHero)
        }else{
            showError()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun hideKeyBoard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Ocurrio un error , no se encontro el super heroe", Toast.LENGTH_SHORT)
            .show()
    }
}