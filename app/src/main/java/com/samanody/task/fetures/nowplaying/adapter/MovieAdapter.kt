package com.samanody.task.fetures.nowplaying.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samanody.domain.models.MovieDto
import com.samanody.task.R

class MovieAdapter(
    private val onClick: (MovieDto) -> Unit
) : ListAdapter<MovieDto, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.moviePic)
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieDto>() {
        override fun areItemsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position) // Get the current movie item
        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        holder.poster.load(imageUrl) // Loading image using Coil
        holder.itemView.setOnClickListener {
            onClick(movie) // Handling item click
        }
    }
}
