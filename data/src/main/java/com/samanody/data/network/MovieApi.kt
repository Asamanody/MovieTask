package com.samanody.data.network


import com.samanody.domain.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): MovieResponse
}