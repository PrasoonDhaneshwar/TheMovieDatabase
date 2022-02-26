package com.prasoon.themoviedatabase.data.remote.dto

import com.prasoon.themoviedatabase.domain.model.Movie

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun Result.toMovie(): Movie {
    return Movie(
        backdropPath = backdrop_path,
        id = id,
        title = title,
        rating = vote_average
    )
}