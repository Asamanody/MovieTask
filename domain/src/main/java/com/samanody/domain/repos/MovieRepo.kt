package com.samanody.domain.repos

import com.samanody.domain.models.MovieDto
import com.samanody.domain.models.MovieResponse

interface MovieRepo {
    suspend fun getNowPlaying(page: Int): MovieResponse
    suspend fun getCachedMovies(): List<MovieDto>
}