package com.samanody.domain.repos

import com.samanody.domain.models.MovieResponse

interface MovieRepo {
    suspend fun getNowPlaying(page: Int): MovieResponse
}