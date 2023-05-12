package com.menesdurak.appterncasestudy.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.TrackData
import com.menesdurak.appterncasestudy.util.convertLengthToMinAndSec

class TrackAdapter(
    private val albumImageLink: String,
    private val favoriteTracksIdList: List<Int>
) :
    RecyclerView.Adapter<TrackHolder>() {

    private var itemList = mutableListOf<TrackData>()
    private lateinit var playListener: OnPlayClickListener
    private lateinit var favoriteListener: OnFavoriteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        return TrackHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false),
            playListener, favoriteListener
        )

    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTrackName).text = itemList[position].title
        holder.itemView.findViewById<TextView>(R.id.tvTrackLength).text =
            convertLengthToMinAndSec(itemList[position].duration)

        Glide
            .with(holder.itemView.context)
            .load(albumImageLink)
            .fitCenter()
            .placeholder(R.drawable.loading)
            .into(holder.itemView.findViewById(R.id.ivTrack))
        if (itemList[position].id in favoriteTracksIdList) {
            holder.itemView.findViewById<ImageView>(R.id.ivFavorite)
                .setImageResource(R.drawable.ic_favorite_filled)
        } else {
            holder.itemView.findViewById<ImageView>(R.id.ivFavorite)
                .setImageResource(R.drawable.ic_favorite_empty)
        }
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
    fun updateList(newList: List<TrackData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}