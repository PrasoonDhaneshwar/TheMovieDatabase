package com.prasoon.themoviedatabase.presentation.movie_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prasoon.themoviedatabase.common.ImageUtils.loadImage
import com.prasoon.themoviedatabase.data.remote.dto.Result
import com.prasoon.themoviedatabase.databinding.ItemMovieBinding

// 2.                                                                                        Link the View holder
// 3.               initialize list in adapter
class MovieListAdapter(private val movieList: ArrayList<Result>, val action: ListAction) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    val TAG = "MovieListAdapter"

    //1. Create View Holder
    inner class MovieListViewHolder(val itemMovieBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun bind (movie: Result) {

            itemMovieBinding.itemMovieTitle.text = movie.title
            itemMovieBinding.itemMovieRating.text = movie.vote_average.toString()
            itemMovieBinding.itemMovieImage.loadImage("https://image.tmdb.org/t/p/w500"+ movie.backdrop_path, false, itemMovieBinding.itemMovieProgress)

            itemMovieBinding.root.setOnClickListener {
                Log.i(TAG, "clicked for: ${movie.id}")
                action.onItemClickDetail(movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val itemBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie: Result = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movieList.size
    fun updateList(MovieListInUI: List<Result>) {
        movieList.clear()
        movieList.addAll(MovieListInUI)
        notifyDataSetChanged()
    }
}