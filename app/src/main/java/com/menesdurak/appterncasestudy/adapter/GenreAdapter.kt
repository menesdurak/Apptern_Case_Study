package com.menesdurak.appterncasestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.GenreData

class GenreAdapter(
    private val list: List<GenreData>,
    private val onItemClicked: (GenreData) -> Unit
) : RecyclerView.Adapter<GenreHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        return GenreHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        ){
            onItemClicked(list[it])
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvGenreName).text = list[position].name
        Glide
            .with(holder.itemView.context)
            .load(list[position].picture_medium)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(holder.itemView.findViewById(R.id.ivGenre))
    }
}