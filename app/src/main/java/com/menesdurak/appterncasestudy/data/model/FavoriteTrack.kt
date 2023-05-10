package com.menesdurak.appterncasestudy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tracks_table")
data class FavoriteTrack (
    val remoteId: Int,
    val name: String,
    val length: Int,
    val imageLink: String,
    val previewLink: String
) {
    @PrimaryKey(autoGenerate = true)
    var localId: Int = 0
}