package com.menesdurak.appterncasestudy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tracks_table")
data class FavoriteTrack (
    @PrimaryKey(autoGenerate = true)
    val localId: Int,
    val remoteId: Int,
    val name: String,
    val length: Int,
    val imageLink: String
)