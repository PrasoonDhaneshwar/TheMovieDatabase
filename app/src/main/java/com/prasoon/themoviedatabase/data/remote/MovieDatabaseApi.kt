package com.prasoon.themoviedatabase.data.remote

import com.prasoon.themoviedatabase.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatabaseApi {
    @GET("/3/movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ) : MovieDto
}

// https://api.themoviedb.org/3/movie/now_playing?api_key=38a73d59546aa378980a88b645f487fc&language=en-US&page=1