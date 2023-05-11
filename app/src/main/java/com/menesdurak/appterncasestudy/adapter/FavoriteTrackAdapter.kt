package com.menesdurak.appterncasestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.FavoriteTrack

class FavoriteTrackAdapter(private val list: List<FavoriteTrack>) :
    RecyclerView.Adapter<FavoriteTrackHolder>() {

    private lateinit var playListener: OnPlayClickListener
    private lateinit var favoriteListener: OnFavoriteClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTrackHolder {
        return FavoriteTrackHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false),
            playListener, favoriteListener
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteTrackHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTrackName).text = list[position].name
        holder.itemView.findViewById<TextView>(R.id.tvTrackLength).text =
            convertLengthToMinAndSec(list[position].length)

        holder.itemView.findViewById<ImageView>(R.id.ivFavorite)
            .setImageResource(R.drawable.ic_favorite_filled)
        Glide
            .with(holder.itemView.context)
            .load(list[position].imageLink)
            .fitCenter()
            .placeholder(R.drawable.loading)
            .into(holder.itemView.findViewById(R.id.ivTrack))
    }

    interface OnPlayClickListener {

        fun onPlayClick(position: Int)
    }

    fun setOnPlayClickListener(listener: OnPlayClickListener) {
        playListener = listener
    }

    interface OnFavoriteClickListener {

        fun onFavoriteClick(position: Int)
    }

    fun setOnFavoriteClickListener(listener: OnFavoriteClickListener) {
        favoriteListener = listener
    }

    private fun convertLengthToMinAndSec(trackLength: Int): String {
        val minute = trackLength / 60
        val second = trackLength % 60
        return "$minute:$second\""
    }
}