package com.menesdurak.appterncasestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.FavoriteTrack

class FavoriteTrackAdapter(private val list: List<FavoriteTrack>) :
    RecyclerView.Adapter<FavoriteTrackHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTrackHolder {
        return FavoriteTrackHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteTrackHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTrackName).text = list[position].name
        holder.itemView.findViewById<TextView>(R.id.tvTrackLength).text =
            list[position].length.toString()
        Glide
            .with(holder.itemView.context)
            .load(list[position].imageLink)
            .fitCenter()
            .placeholder(R.drawable.loading)
            .into(holder.itemView.findViewById(R.id.ivTrack))
    }
}