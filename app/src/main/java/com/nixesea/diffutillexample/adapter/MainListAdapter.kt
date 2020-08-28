package com.nixesea.diffutillexample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nixesea.diffutillexample.R
import com.nixesea.diffutillexample.model.Content

class MainListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<Content> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_list_item, parent, false);
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainViewHolder) {
            holder.setName(items[position].name)
            holder.setAddress(items[position].address)
            holder.setDistance(items[position].distance)
            holder.setStars(items[position].stars)
        }
    }

    override fun getItemCount(): Int = items.size


    fun updateItems(newItems: ArrayList<Content>) {
        items.clear()
        items.addAll(newItems)

        dispatchNewList(emptyList(), items)
    }

    fun sortByName() {
        val oldItems: ArrayList<Content> = ArrayList(items)
        items.sortBy { it.name }
        dispatchNewList(oldItems, items)
    }

    fun sortByDistance() {
        val oldItems: ArrayList<Content> = ArrayList(items)
        items.sortBy { it.distance }
        dispatchNewList(oldItems, items)

    }

    fun sortByStar() {
        val oldItems: ArrayList<Content> = ArrayList(items)
        items.sortBy { it.stars }
        dispatchNewList(oldItems, items)

    }

    private fun dispatchNewList(
        oldItems: List<Content>,
        newItems: List<Content>
    ) {
        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(SortingDiffUtil(oldItems, newItems))
        diffResult.dispatchUpdatesTo(this)
        if (oldItems.isNotEmpty() && newItems.isNotEmpty()) {
            Log.i("TEST", "dispatchUpdatesTo|${oldItems.first().id}|${newItems.first().id}")
        }
    }

}

class SortingDiffUtil(
    private val oldItems: List<Content>,
    private val newItems: List<Content>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}