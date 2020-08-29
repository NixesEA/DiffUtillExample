package com.nixesea.diffutillexample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nixesea.diffutillexample.R
import com.nixesea.diffutillexample.model.ListItemsType

class MainListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<ListItemsType> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_list_item, parent, false);
                return MainViewHolder(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_list_item, parent, false);
                return SecondViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_list_item, parent, false);
                return MainViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is ListItemsType.Content -> {
            1
        }
        is ListItemsType.SecondType -> {
            2
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> {
                val item = items[position] as ListItemsType.Content
                holder.setName(item.name)
                holder.setAddress(item.address)
                holder.setDistance(item.distance)
                holder.setStars(item.stars)
            }
            is SecondViewHolder -> {
                val item = items[position] as ListItemsType.SecondType
                holder.setName(item.name)
            }
        }
    }

    override fun getItemCount(): Int = items.size


    fun updateItems(newItems: ArrayList<ListItemsType>) {
        items.clear()
        items.addAll(newItems)

        dispatchNewList(emptyList(), items)
    }

    fun sortByName() {
        val oldItems: ArrayList<ListItemsType> = ArrayList(items)
        val newItems: ArrayList<ListItemsType> = ArrayList(
            items.sortedBy { it.name }
        )

        items.clear()
        items.addAll(newItems)
        dispatchNewList(oldItems, newItems)
    }

    fun sortByDistance() {
        val oldItems: ArrayList<ListItemsType> = ArrayList(items)
        val newItems: ArrayList<ListItemsType> = ArrayList(
            items.filterIsInstance<ListItemsType.Content>().sortedBy { it.distance }
        )
        newItems.addAll(
            oldItems.filterIsInstance<ListItemsType.SecondType>()
        )

        items.clear()
        items.addAll(newItems)
        dispatchNewList(oldItems, newItems)

    }

    fun sortByStar() {
        val oldItems: ArrayList<ListItemsType> = ArrayList(items)
        val newItems: ArrayList<ListItemsType> = ArrayList(
            items.filterIsInstance<ListItemsType.Content>().sortedBy { it.stars }
        )
        newItems.addAll(
            oldItems.filterIsInstance<ListItemsType.SecondType>()
        )
        items.clear()
        items.addAll(newItems)

        dispatchNewList(oldItems, newItems)

    }

    private fun dispatchNewList(
        oldItems: List<ListItemsType>,
        newItems: List<ListItemsType>
    ) {
        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(SortingDiffUtil(oldItems, newItems))
        diffResult.dispatchUpdatesTo(this)
        if (oldItems.isNotEmpty() && newItems.isNotEmpty()) {
            Log.i("TEST", "dispatchUpdatesTo|${oldItems.first().id}|${newItems.first().id}")
            Log.i("TEST", "oldItems|${oldItems}")
            Log.i("TEST", "newItems|${newItems}")
        }
    }

}

class SortingDiffUtil(
    private val oldItems: List<ListItemsType>,
    private val newItems: List<ListItemsType>
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