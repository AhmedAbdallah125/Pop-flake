package com.ahmed_abdallah.pop_flake.model.data.repository

import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse
import com.ahmed_abdallah.pop_flake.pojo.*


interface IRepository {
    suspend fun getInTheatresMovies(
    ): NetworkResponse<MovieAPI>

    suspend fun getComingSoonMovies(
    ): NetworkResponse<MovieAPI>

    suspend fun getTop250Movies(
    ): NetworkResponse<TopRatedMovieAPI>

    suspend fun getBoxOfficeMovies(
    ): NetworkResponse<TopMoviesOfficeAPI>

    suspend fun searchForMovieOrSeries(
        searchKey: String
    ): NetworkResponse<SearchResultAPI>


    suspend fun searchForTrailer(
        searchKey: String
    ): NetworkResponse<Trailer>

    suspend fun searchForPoster(
        searchKey: String
    ): NetworkResponse<PosterAPI>

    fun setMode(mode: Int)
    fun getMode(): Int
}