package com.menesdurak.appterncasestudy.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AlbumHolder(view: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

    init {
        itemView.setOnClickListener {
            clickAtPosition(adapterPosition)
        }
    }
}