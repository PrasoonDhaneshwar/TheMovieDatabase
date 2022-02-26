package com.prasoon.themoviedatabase.presentation.movie_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prasoon.themoviedatabase.common.Constants
import com.prasoon.themoviedatabase.common.Resource
import com.prasoon.themoviedatabase.domain.use_case.get_movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var movieListState = MovieListState()
    val movieListLiveData = MutableLiveData<MovieListState>()

    fun refresh(api_key: String) {
        getMovies(api_key)
    }

    fun getMovies(api_key: String) {
        getMoviesUseCase(api_key).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    movieListState = MovieListState(movies = result.data ?: emptyList())
                    movieListLiveData.postValue(movieListState)
                }
                is Resource.Error -> {
                    movieListState =
                        MovieListState(error = result.message ?: "An unexpected error occurred")
                    movieListLiveData.postValue(movieListState)
                }
                is Resource.Loading -> {
                    movieListState = MovieListState(isLoading = true)
                    movieListLiveData.postValue(movieListState)
                }
            }
        }.launchIn(viewModelScope)      // Step 26: Launch coroutine in a viewModelScope
    }

    // Step 27: Initialize the view model
    init {
        savedStateHandle.get<String>(Constants.PARAM_API_KEY)?.let { api_key ->
            getMovies(api_key)
        }
    }
}