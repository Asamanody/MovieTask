package com.samanody.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.samanody.domain.Resource.Resource
import com.samanody.task.databinding.FragmentNowPlayingBinding
import com.samanody.task.fetures.nowplaying.NowPlayingViewModel
import com.samanody.task.fetures.nowplaying.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding !!
    private val viewModel: NowPlayingViewModel by viewModels()
    private lateinit var adapter: MovieAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNowPlayingBinding.bind(requireView())

        lifecycleScope.launch {
            viewModel.moviesState.collectLatest { state ->
                when (state) {
                    is Resource.Success-> {
                        adapter = MovieAdapter { movie ->
                            // TODO: Navigate to details
                        }
                        binding.movieRecyclerView.apply {
                            layoutManager= GridLayoutManager(requireContext(), 2)
                            adapter = this@NowPlayingFragment.adapter
                        }
                        adapter.submitList(state.data)

                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    is Resource.Loading -> {
                        // todo show loading state

                    }
                    is Resource.Empty -> Unit
                    is Resource.Exception -> Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        viewModel.loadMovies()
        binding.searchField.addTextChangedListener {
            viewModel.filterMovies(it.toString())
        }
        viewModel.loadMovies()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}