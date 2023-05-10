package com.menesdurak.appterncasestudy.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.menesdurak.appterncasestudy.R

class TrackHolder(
    view: View,
    playAtPosition: TrackAdapter.OnPlayClickListener,
    favoriteAtPosition: TrackAdapter.OnFavoriteClickListener
) : RecyclerView.ViewHolder(view) {
    init {
        itemView.findViewById<TextView>(R.id.tvTransparent).setOnClickListener {
            playAtPosition.onPlayClick(adapterPosition)
        }
        itemView.findViewById<ImageView>(R.id.ivFavorite).setOnClickListener {
            favoriteAtPosition.onFavoriteClick(adapterPosition)
        }
    }
}