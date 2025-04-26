package com.samanody.data.reposImpl

import com.samanody.data.BuildConfig
import com.samanody.data.locale.MovieDao
import com.samanody.data.network.MovieApi
import com.samanody.domain.mapper.toDto
import com.samanody.domain.mapper.toEntity
import com.samanody.domain.models.MovieDto
import com.samanody.domain.models.MovieResponse
import com.samanody.domain.repos.MovieRepo
import javax.inject.Inject

class MovieRepoImpl (
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepo {

    override suspend fun getNowPlaying(page: Int): MovieResponse {
        val response= api.getNowPlaying(page,  BuildConfig.TMDB_API_KEY)
        if (page == 1) {
            dao.clearAll()
        }
        dao.insertMovies(response.results.map { it.toEntity() })
        return response
    }
    override suspend fun getCachedMovies(): List<MovieDto> {
        return dao.getAllMovies().map { it.toDto() }
    }
}