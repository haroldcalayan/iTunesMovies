package com.haroldcalayan.feature.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.common.base.BaseViewModel
import com.haroldcalayan.data.MovieRepository
import com.haroldcalayan.data.model.Movie
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MasterViewModel : BaseViewModel() {

    @Inject
    lateinit var movieRepository: MovieRepository

    private val _movies = MutableLiveData<List<Movie>>()

    init {
        getCachedMovies()
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val request = runCatching { movieRepository.getAllItems() }
            request.apply {
                onSuccess {
                    Timber.d("movies: $it")
                    _movies.postValue(it)
                }
                onFailure {
                    it.printStackTrace()
                }
            }
        }
    }

    private fun getCachedMovies() {
        viewModelScope.launch {
            val request = runCatching { movieRepository.getCachedItems() }
            request.apply {
                onSuccess {
                    Timber.d("movies: $it")
                    _movies.postValue(it)
                }
                onFailure {
                    it.printStackTrace()
                }
            }
        }
    }

    /*
    LiveData Getters
     */
    val movies: LiveData<List<Movie>>
        get() = _movies
}