package com.menesdurak.appterncasestudy.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.FavoriteTrack
import com.menesdurak.appterncasestudy.util.convertLengthToMinAndSec

class FavoriteTrackAdapter :
    RecyclerView.Adapter<FavoriteTrackHolder>() {

    private var itemList = mutableListOf<FavoriteTrack>()
    private lateinit var playListener: OnPlayClickListener
    private lateinit var favoriteListener: OnFavoriteClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTrackHolder {
        return FavoriteTrackHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false),
            playListener, favoriteListener
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: FavoriteTrackHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTrackName).text = itemList[position].name
        holder.itemView.findViewById<TextView>(R.id.tvTrackLength).text =
            convertLengthToMinAndSec(itemList[position].length)

        holder.itemView.findViewById<ImageView>(R.id.ivFavorite)
            .setImageResource(R.drawable.ic_favorite_filled)
        Glide
            .with(holder.itemView.context)
            .load(itemList[position].imageLink)
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<FavoriteTrack>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}