package com.nixesea.diffutillexample.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nixesea.diffutillexample.R

class SecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.item_name)

    fun setName(message: String) {
        name.text = message
    }

}