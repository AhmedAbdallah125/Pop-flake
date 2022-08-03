package com.ahmed_abdallah.pop_flake.model.data.remoteSource

import com.ahmed_abdallah.pop_flake.pojo.*
import retrofit2.Response

interface RemoteSource {
    suspend fun getInTheatresMovies(
    ): Response<MovieAPI>

    suspend fun getComingSoonMovies(
    ): Response<MovieAPI>

    suspend fun getTop250Movies(
    ): Response<TopRatedMovieAPI>

    suspend fun getBoxOfficeMovies(
    ): Response<TopMoviesOfficeAPI>

    suspend fun searchForMovieOrSeries(
        searchKey: String
    ): Response<SearchResultAPI>

    suspend fun searchForTrailer(
        searchKey: String
    ): Response<Trailer>

    suspend fun searchForPoster(
        searchKey: String
    ): Response<PosterAPI>
}