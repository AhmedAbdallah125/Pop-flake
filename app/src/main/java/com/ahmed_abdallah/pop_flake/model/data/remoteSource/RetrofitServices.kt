package com.ahmed_abdallah.pop_flake.model.data.remoteSource

import com.ahmed_abdallah.pop_flake.pojo.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val APIKEY = "k_fpxwt363"

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
    ): Response<TopRatedMovieAPI>

    @GET("BoxOffice")
    suspend fun getBoxOfficeMovies(
        @Query("apiKey") apiKey: String = APIKEY
    ): Response<TopMoviesOfficeAPI>

    @GET("SearchTitle")
    suspend fun searchForMovieOrSeries(
        @Query("apiKey") apiKey: String = APIKEY,
        @Query("expression") searchKey: String
        ): Response<SearchResultAPI>

    @GET("Trailer")
    suspend fun searchForTrailer(
        @Query("apiKey") apiKey: String = APIKEY,
        @Query("id") searchKey: String
    ): Response<Trailer>

    @GET("Posters")
    suspend fun searchForPoster(
        @Query("apiKey") apiKey: String = APIKEY,
        @Query("id") searchKey: String
    ): Response<PosterAPI>
}