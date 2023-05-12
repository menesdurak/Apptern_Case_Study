package com.menesdurak.appterncasestudy.data.local

import com.menesdurak.appterncasestudy.data.model.FavoriteTrack
import javax.inject.Inject

class FavoriteTrackRepository @Inject constructor(private val favoriteTrackDao: FavoriteTrackDao) {

    suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack) {
        favoriteTrackDao.addFavoriteTrack(favoriteTrack)
    }

    suspend fun deleteFavoriteTrackWithId(favoriteTrackId: Long) {
        favoriteTrackDao.deleteFavoriteTrackWithId(favoriteTrackId)
    }

    suspend fun getAllFavoriteTracks(): List<FavoriteTrack> =
        favoriteTrackDao.getAllFavoriteTracks()
}