package com.bab.caching.di

import android.content.Context
import androidx.work.CoroutineWorker
import com.samanody.data.locale.MovieDao
import com.samanody.data.network.MovieApi
import com.samanody.data.reposImpl.MovieRepoImpl
import com.samanody.data.worker.MovieWorker
import com.samanody.domain.repos.MovieRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
 class AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }



    @Provides
    @Singleton
    fun provideMovieRepo(api: MovieApi,
                         dao: MovieDao
    ): MovieRepo {
        return MovieRepoImpl(api,dao)
    }


}