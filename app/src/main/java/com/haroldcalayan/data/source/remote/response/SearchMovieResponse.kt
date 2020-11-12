package com.haroldcalayan.data.source.remote.response

import com.haroldcalayan.data.model.Movie

data class SearchMovieResponse(val resultCount: Int, val results: List<Movie>)