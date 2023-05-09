package com.menesdurak.appterncasestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.AlbumData

class AlbumAdapter(private val list: List<AlbumData>) : RecyclerView.Adapter<AlbumHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        return AlbumHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvAlbumName).text = list[position].title
        holder.itemView.findViewById<TextView>(R.id.tvAlbumReleaseDate).text = list[position].release_date
        Glide
            .with(holder.itemView.context)
            .load(list[position].cover_medium)
            .fitCenter()
            .placeholder(R.drawable.loading)
            .into(holder.itemView.findViewById(R.id.ivAlbum))
    }
}