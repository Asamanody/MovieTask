package com.samanody.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samanody.domain.models.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}