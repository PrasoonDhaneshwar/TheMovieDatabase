package com.prasoon.themoviedatabase.domain.use_case.get_movies

import com.prasoon.themoviedatabase.common.Resource
import com.prasoon.themoviedatabase.data.remote.dto.Result
import com.prasoon.themoviedatabase.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(api_key: String): Flow<Resource<List<Result>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getMovies(api_key).results
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}