package com.haroldcalayan.data.source.remote

import com.haroldcalayan.common.Constants
import com.haroldcalayan.data.source.remote.response.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun searchMovies(
        @Query(Constants.PARAM_TERM) term: String,
        @Query(Constants.PARAM_COUNTRY) country: String,
        @Query(Constants.PARAM_MEDIA) media: String
        ): SearchMovieResponse

}