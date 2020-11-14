package com.haroldcalayan.data

import android.content.Context
import com.haroldcalayan.ITunesMoviesApplication
import com.haroldcalayan.common.Constants
import com.haroldcalayan.data.model.Movie
import com.haroldcalayan.data.source.local.ITunesMoviesDatabase
import com.haroldcalayan.data.source.remote.ApiClient
import com.haroldcalayan.utils.NetworkUtils
import javax.inject.Inject

class MovieRepositoryImpl(private val context: Context,
                          private val appDatabase: ITunesMoviesDatabase) : MovieRepository() {

    @Inject
    lateinit var api: ApiClient

    init {
        ITunesMoviesApplication.instance.appComponent.inject(this)
    }

    @Throws(java.lang.Exception::class)
    override suspend fun getAllItems(): List<Movie> {
        return if (NetworkUtils.isNetworkConnected(context)) {
            getMoviesFromRemote()
        } else {
            getMoviesFromDatabase()
        }
    }

    override suspend fun getCachedItems() = getMoviesFromDatabase()

    @Throws(java.lang.Exception::class)
    override suspend fun getSomeItems(limit: Int, offset: Int): List<Movie> {
        var movies = appDatabase.movieDao().getSomeMovies(limit, offset)
        if(movies?.isNotEmpty()) return movies
        getMoviesFromRemote()
        return appDatabase.movieDao().getSomeMovies(limit, offset)
    }

    override suspend fun insertItem(movie: Movie) = appDatabase.movieDao().insert(movie)

    override suspend fun insertItem(list: List<Movie>) = appDatabase.movieDao().insertMovies(list)

    override suspend fun updateItem(movie: Movie) = appDatabase.movieDao().updateMovie(movie)

    override suspend fun deleteItem() = appDatabase.movieDao().deleteAll()

    private suspend fun getMoviesFromDatabase() = appDatabase.movieDao().getAllMovies()

    @Throws(java.lang.Exception::class)
    private suspend fun getMoviesFromRemote() : List<Movie> {
        var response = api.getService()?.searchMovies(Constants.VALUE_TERM, Constants.VALUE_COUNTRY, Constants.VALUE_MEDIA)
        var movies = response?.results
        appDatabase.movieDao().deleteAll()
        if (movies != null) appDatabase.movieDao().insertMovies(movies)
        return movies ?: emptyList()
    }
}