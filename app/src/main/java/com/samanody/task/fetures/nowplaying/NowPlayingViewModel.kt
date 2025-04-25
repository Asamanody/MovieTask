package com.samanody.task.fetures.nowplaying
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samanody.domain.Resource.Resource
import com.samanody.domain.models.MovieDto
import com.samanody.domain.usecases.GetNowPlayingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NowPlayingViewModel  @Inject constructor(
    private val getNowPlayingUseCase: GetNowPlayingUseCase
) : ViewModel() {


    private val _moviesState = MutableStateFlow<Resource<List<MovieDto>>>(Resource.Empty())
    val moviesState = _moviesState.asStateFlow()

    private var allMovies = listOf<MovieDto>()

    fun loadMovies() {
        viewModelScope.launch {
            _moviesState.value = Resource.Loading()
            //todo
            val result = getNowPlayingUseCase(1)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                   _moviesState.value = Resource.Success(it.results)
                    allMovies= it.results
                    }
                }
            else {
                _moviesState.value = Resource.Exception(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun filterMovies(query: String) {
        _moviesState.value =
        if (query.isEmpty()) Resource.Success<List<MovieDto>>(allMovies)
        else Resource.Success(this.allMovies.filter { it.title.contains(query, ignoreCase = true) })

    }
}