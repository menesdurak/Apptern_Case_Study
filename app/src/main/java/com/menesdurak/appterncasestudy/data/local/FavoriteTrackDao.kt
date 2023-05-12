package com.menesdurak.appterncasestudy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.menesdurak.appterncasestudy.data.model.FavoriteTrack

@Dao
interface FavoriteTrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack)

    @Query("DELETE FROM favorite_tracks_table WHERE id = :favoriteTrackId")
    suspend fun deleteFavoriteTrackWithId(favoriteTrackId: Int)

    @Query("SELECT * FROM favorite_tracks_table ORDER BY id ASC")
    suspend fun getAllFavoriteTracks(): List<FavoriteTrack>

}