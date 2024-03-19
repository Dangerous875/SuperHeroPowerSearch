package com.example.superheropotente.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.superheropotente.data.SuperHeroItem

class SuperHeroDiffUtil(private val oldList: List<SuperHeroItem> , private val newList : List<SuperHeroItem>):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}