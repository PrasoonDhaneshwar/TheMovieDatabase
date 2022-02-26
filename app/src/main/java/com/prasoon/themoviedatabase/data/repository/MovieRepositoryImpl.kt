package com.prasoon.themoviedatabase.data.repository

import com.prasoon.themoviedatabase.data.remote.MovieDatabaseApi
import com.prasoon.themoviedatabase.data.remote.dto.MovieDto
import com.prasoon.themoviedatabase.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieDatabaseApi
) : MovieRepository {
    override suspend fun getMovies(api_key: String): MovieDto {
        return api.getMovies(api_key)
    }
}