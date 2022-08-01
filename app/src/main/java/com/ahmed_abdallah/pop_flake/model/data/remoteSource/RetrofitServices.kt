package com.ahmed_abdallah.pop_flake.model.data.remoteSource

import com.ahmed_abdallah.pop_flake.pojo.MovieAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val APIKEY = "k_hxki9v6d"

//https://imdb-api.com/en/API/InTheaters/k_hxki9v6d
interface RetrofitServices {
    @GET("InTheaters")
    suspend fun getInTheatresMovies(
        @Query("apiKey") apiKey: String = APIKEY
    ): Response<MovieAPI>
}