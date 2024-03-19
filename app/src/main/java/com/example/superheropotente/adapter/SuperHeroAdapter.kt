package com.example.superheropotente.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheropotente.data.SuperHeroItem
import com.example.superheropotente.databinding.ItemSuperheroBinding
import com.example.superheropotente.diffutil.SuperHeroDiffUtil

class SuperHeroAdapter(
    private var heroList: List<SuperHeroItem>,
    private val onClickSuperHero: (SuperHeroItem) -> Unit
):RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {
    class SuperHeroViewHolder (val binding : ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SuperHeroViewHolder(binding)
    }

    override fun getItemCount(): Int = heroList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = heroList[position]
        val bind = holder.binding
        val context = bind.root.context

        instanceView(item,bind,context)
    }

    fun updateSuperHeroList(newList : List<SuperHeroItem>){
        val superHeroDiffUtil = SuperHeroDiffUtil(heroList,newList)
        val result : DiffUtil.DiffResult = DiffUtil.calculateDiff(superHeroDiffUtil)
        heroList = newList
        result.dispatchUpdatesTo(this)
    }

    private fun instanceView(item: SuperHeroItem, bind: ItemSuperheroBinding, context: Context) {
        bind.tvSuperheroName.text = item.name
        Glide.with(context)
            .load(item.image.url)
            .into(bind.ivSuperhero)

        bind.cardSuperHero.setOnClickListener {
            onClickSuperHero(item)
        }
    }
}