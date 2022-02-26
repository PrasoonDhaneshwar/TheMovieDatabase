package com.prasoon.themoviedatabase.presentation.movie_list

import com.prasoon.themoviedatabase.data.remote.dto.Result

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Result> = emptyList(),
    val error: String = ""
)
