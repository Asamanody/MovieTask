package com.samanody.data.di

import android.content.Context
import androidx.room.Room
import com.samanody.data.locale.MovieDao
import com.samanody.data.locale.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_db"
        ).build()

    @Provides
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao()


}

