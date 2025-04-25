package com.samanody.data.reposImpl

import com.samanody.data.BuildConfig
import com.samanody.data.network.MovieApi
import com.samanody.domain.models.MovieResponse
import com.samanody.domain.repos.MovieRepo
import javax.inject.Inject

class MovieRepoImpl (
    private val api: MovieApi
) : MovieRepo {

    override suspend fun getNowPlaying(page: Int): MovieResponse {
        return api.getNowPlaying(page,  BuildConfig.TMDB_API_KEY)
    }
}