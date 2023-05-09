package com.menesdurak.appterncasestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.ArtistData

class ArtistAdapter(private val list: List<ArtistData>) : RecyclerView.Adapter<ArtistHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        return ArtistHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvArtistName).text = list[position].name
        Glide
            .with(holder.itemView.context)
            .load(list[position].picture_medium)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(holder.itemView.findViewById(R.id.ivArtist))
    }
}