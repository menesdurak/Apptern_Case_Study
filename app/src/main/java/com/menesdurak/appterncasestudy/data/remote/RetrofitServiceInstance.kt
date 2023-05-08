package com.menesdurak.appterncasestudy.data.remote

import com.menesdurak.appterncasestudy.data.model.Genre
import retrofit2.http.GET

interface RetrofitServiceInstance {

    @GET("genre")
    suspend fun getGenres(): Genre
}