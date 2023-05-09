package com.menesdurak.appterncasestudy.data.remote

import com.menesdurak.appterncasestudy.data.model.Album
import com.menesdurak.appterncasestudy.data.model.Artist
import com.menesdurak.appterncasestudy.data.model.Genre
import com.menesdurak.appterncasestudy.data.model.Track
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance: RetrofitServiceInstance) {

    suspend fun getGenres(): Genre = retrofitServiceInstance.getGenres()

    suspend fun getArtists(genreId: Int): Artist = retrofitServiceInstance.getArtists(genreId)

    suspend fun getAlbums(artistId: Int): Album = retrofitServiceInstance.getAlbums(artistId)

    suspend fun getTracks(albumId: Int): Track = retrofitServiceInstance.getTracks(albumId)
}