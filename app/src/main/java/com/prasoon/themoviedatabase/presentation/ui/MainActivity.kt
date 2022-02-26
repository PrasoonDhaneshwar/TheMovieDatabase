package com.prasoon.themoviedatabase.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.prasoon.themoviedatabase.common.Constants
import com.prasoon.themoviedatabase.databinding.ActivityMainBinding
import com.prasoon.themoviedatabase.presentation.movie_list.ListAction
import com.prasoon.themoviedatabase.presentation.movie_list.MovieListAdapter
import com.prasoon.themoviedatabase.presentation.movie_list.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ListAction {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MovieListViewModel by viewModels()

    private val movieListAdapter = MovieListAdapter(arrayListOf(), this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 2. Set before setContentView
        binding = ActivityMainBinding.inflate(layoutInflater)

        // setContentView(R.layout.activity_main)
        // 3. Replace R.layout.activity_main with binding
        setContentView(binding.root)

        viewModel.refresh(api_key = Constants.PARAM_API_KEY)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
        }
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.movieListLiveData.observe(this) { movieList ->
            if (!movieList.isLoading)
                movieListAdapter.updateList(movieList.movies)
            if (movieList.error.isNotEmpty()) {
                Toast.makeText(this, "Unexpected error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClickDetail(id: Int) {
        TODO("Not yet implemented")
    }
}