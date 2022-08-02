package com.ahmed_abdallah.pop_flake.model.data.remoteSource

import com.ahmed_abdallah.pop_flake.pojo.BoxOfficeMovie
import com.ahmed_abdallah.pop_flake.pojo.MovieAPI
import com.ahmed_abdallah.pop_flake.pojo.TopRatedMovie
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

    @GET("ComingSoon")
    suspend fun getComingSoonMovies(
        @Query("apiKey") apiKey: String = APIKEY
    ): Response<MovieAPI>

    @GET("Top250Movies")
    suspend fun getTop250Movies(
        @Query("apiKey") apiKey: String = APIKEY
    ): Response<TopRatedMovie>
    @GET("BoxOffice")
    suspend fun getBoxOfficeMovies(
        @Query("apiKey") apiKey: String = APIKEY
    ): Response<BoxOfficeMovie>
}