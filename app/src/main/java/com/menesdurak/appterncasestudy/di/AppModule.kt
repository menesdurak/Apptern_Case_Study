package com.menesdurak.appterncasestudy.di

import android.app.Application
import com.menesdurak.appterncasestudy.data.local.FavoriteTrackDao
import com.menesdurak.appterncasestudy.data.local.FavoriteTrackDatabase
import com.menesdurak.appterncasestudy.data.remote.RetrofitServiceInstance
import com.menesdurak.appterncasestudy.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit): RetrofitServiceInstance {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): FavoriteTrackDatabase {
        return FavoriteTrackDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getFavoriteTrackDao(favoriteTrackDatabase: FavoriteTrackDatabase): FavoriteTrackDao {
        return favoriteTrackDatabase.getFavoriteTrackDao()
    }
}