package com.menesdurak.appterncasestudy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tracks_table")
data class FavoriteTrack (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val length: Int,
    val imageLink: String,
    val previewLink: String
)