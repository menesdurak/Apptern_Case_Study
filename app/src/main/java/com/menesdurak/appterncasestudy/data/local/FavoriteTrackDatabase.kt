package com.menesdurak.appterncasestudy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.menesdurak.appterncasestudy.data.model.FavoriteTrack

@Database(entities = [FavoriteTrack::class], version = 1)
abstract class FavoriteTrackDatabase : RoomDatabase() {

    abstract fun getFavoriteTrackDao(): FavoriteTrackDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteTrackDatabase? = null

        fun getDatabase(context: Context): FavoriteTrackDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteTrackDatabase::class.java,
                    "favorite_tracks_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}