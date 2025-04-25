package com.samanody.domain.usecases

import com.samanody.domain.models.MovieResponse
import com.samanody.domain.repos.MovieRepo
import javax.inject.Inject

class GetNowPlayingUseCase   @Inject constructor(
    private val movieRepo: MovieRepo
) {
    suspend operator fun invoke(
        page: Int,
        apiKey: String
    ): Result<MovieResponse?> {
        return try {
            Result.success(movieRepo.getNowPlaying(page, apiKey))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}