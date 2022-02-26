package com.prasoon.themoviedatabase.domain.repository

import com.prasoon.themoviedatabase.data.remote.dto.MovieDto

interface MovieRepository {
    suspend fun getMovies(api_key: String): MovieDto
}