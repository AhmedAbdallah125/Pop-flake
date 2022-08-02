package com.ahmed_abdallah.pop_flake.model

import com.ahmed_abdallah.pop_flake.pojo.MovieAPI
import com.ahmed_abdallah.pop_flake.pojo.SearchResultAPI
import com.ahmed_abdallah.pop_flake.pojo.TopMoviesOfficeAPI
import com.ahmed_abdallah.pop_flake.pojo.TopRatedMovieAPI
import retrofit2.Response


interface IRepository {
    suspend fun getInTheatresMovies(
    ): Response<MovieAPI>

    suspend fun getComingSoonMovies(
    ): Response<MovieAPI>

    suspend fun getTop250Movies(
    ): Response<TopRatedMovieAPI>

    suspend fun getBoxOfficeMovies(
    ): Response<TopMoviesOfficeAPI>

    suspend fun searchForMovieOrSeries(
    ): Response<SearchResultAPI>
}