package com.samanody.task.fetures.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samanody.domain.models.MovieDto
import com.samanody.task.R
import com.samanody.task.databinding.FragmentMovieDetailsBinding
import com.samanody.task.databinding.FragmentNowPlayingBinding
import com.samanody.task.utils.ImageHelper


class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding !!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(requireView())

        val args = arguments?.let { MovieDetailsFragmentArgs.fromBundle(it) }

        binding.apply {
            args?.movie?.let {
                detailsTitle.text = it.title
                detailsReleaseDate.text = "Release Date: ${it.releaseDate}"
                detailsRating.text = "Rating: ${it.voteAverage}"
                detailsOverview.text = it.overview
                val imageUrl = "https://image.tmdb.org/t/p/w500${it.posterPath}"
                ImageHelper.load(detailsPoster, imageUrl)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}