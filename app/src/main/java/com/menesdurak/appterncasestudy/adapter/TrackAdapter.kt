package com.menesdurak.appterncasestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.menesdurak.appterncasestudy.R
import com.menesdurak.appterncasestudy.data.model.TrackData

class TrackAdapter(
    private val list: List<TrackData>,
    private val albumImageLink: String
) :
    RecyclerView.Adapter<TrackHolder>() {

    private lateinit var playListener: OnPlayClickListener
    private lateinit var favoriteListener: OnFavoriteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        return TrackHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false),
            playListener, favoriteListener
        )

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTrackName).text = list[position].title
        holder.itemView.findViewById<TextView>(R.id.tvTrackLength).text =
            list[position].duration.toString()
        Glide
            .with(holder.itemView.context)
            .load(albumImageLink)
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
}