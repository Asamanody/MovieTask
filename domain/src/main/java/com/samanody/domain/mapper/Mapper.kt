package com.samanody.domain.mapper

import com.samanody.domain.models.MovieDto
import com.samanody.domain.models.MovieEntity

fun MovieDto.toEntity(): MovieEntity = MovieEntity(
    id, title, overview, releaseDate, posterPath, voteAverage
)

fun MovieEntity.toDto(): MovieDto = MovieDto(
    id, title, overview, releaseDate, posterPath, voteAverage
)