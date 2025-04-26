package com.samanody.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.samanody.data.BuildConfig
import com.samanody.data.locale.MovieDao
import com.samanody.data.network.MovieApi
import com.samanody.domain.mapper.toEntity
import com.samanody.domain.models.MovieDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MovieWorker  @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val api: MovieApi,
    private val dao: MovieDao
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        try {
            val allMovies = mutableListOf<MovieDto>()
            var page = 1
            var totalPages = 1

            do {
                val response = api.getNowPlaying(page, BuildConfig.TMDB_API_KEY)
                totalPages = response.totalPages
                allMovies.addAll(response.results)
                page++
            } while (page <= totalPages)

            dao.clearAll()
            dao.insertMovies(allMovies.map { it.toEntity() })

            return Result.success()

        } catch (e: Exception) {
            return Result.retry()
        }
    }


}