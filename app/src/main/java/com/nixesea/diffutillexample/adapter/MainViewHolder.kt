package com.nixesea.diffutillexample.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nixesea.diffutillexample.R

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.item_name)
    private val address: TextView = itemView.findViewById(R.id.item_address)
    private val distance: TextView = itemView.findViewById(R.id.item_distance)
    private val stars: TextView = itemView.findViewById(R.id.item_stars)

    fun setName(message: String) {
        name.text = message
    }

    fun setAddress(message: String) {
        address.text = message
    }

    fun setDistance(message: Double) {
        distance.text = message.toString()
    }

    fun setStars(message: Double) {
        stars.text = message.toString()
    }
}