package com.menesdurak.appterncasestudy.data.remote

import com.menesdurak.appterncasestudy.data.model.Genre
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance: RetrofitServiceInstance) {

    suspend fun getGenres(): Genre = retrofitServiceInstance.getGenres()
}