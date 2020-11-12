package com.haroldcalayan.feature.master

import androidx.lifecycle.viewModelScope
import com.haroldcalayan.common.base.BaseViewModel
import com.haroldcalayan.data.MovieRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MasterViewModel : BaseViewModel() {

    @Inject
    lateinit var movieRepository: MovieRepository

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val request = runCatching { movieRepository.getAllItems() }
            request.apply {
                onSuccess {
                    Timber.d("movies: $it")
                }
                onFailure {
                    it.printStackTrace()
                }
            }
        }
    }
}