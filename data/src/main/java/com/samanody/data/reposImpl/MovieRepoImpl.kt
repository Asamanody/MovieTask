package com.samanody.data.reposImpl

import com.samanody.data.network.MovieApi
import com.samanody.domain.models.MovieResponse
import com.samanody.domain.repos.MovieRepo
import javax.inject.Inject

class MovieRepoImpl  constructor(
    private val api: MovieApi
) : MovieRepo {

    override suspend fun getNowPlaying(page: Int,apiKey:String): MovieResponse {
        return api.getNowPlaying(page,  apiKey)
    }
}