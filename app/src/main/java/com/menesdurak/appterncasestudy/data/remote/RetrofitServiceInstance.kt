package com.menesdurak.appterncasestudy.data.remote

import com.menesdurak.appterncasestudy.data.model.Album
import com.menesdurak.appterncasestudy.data.model.Artist
import com.menesdurak.appterncasestudy.data.model.Genre
import com.menesdurak.appterncasestudy.data.model.Track
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServiceInstance {

    @GET("genre")
    suspend fun getGenres(): Genre

    @GET("genre/{genre_id}/artists")
    suspend fun getArtists(@Path("genre_id") genreId: Int): Artist

    @GET("artist/{artist_id}/albums")
    suspend fun getAlbums(@Path("artist_id") artistId: Int): Album

    @GET("album/{album_id}/tracks")
    suspend fun getTracks(@Path("album_id") albumId: Int): Track
}