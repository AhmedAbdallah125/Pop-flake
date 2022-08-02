package com.ahmed_abdallah.pop_flake.model

import com.ahmed_abdallah.pop_flake.model.data.remoteSource.RemoteSource
import com.ahmed_abdallah.pop_flake.pojo.MovieAPI
import com.ahmed_abdallah.pop_flake.pojo.SearchResultAPI
import com.ahmed_abdallah.pop_flake.pojo.TopMoviesOfficeAPI
import com.ahmed_abdallah.pop_flake.pojo.TopRatedMovieAPI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val remoteSource:RemoteSource) :IRepository{
    override suspend fun getInTheatresMovies(): Response<MovieAPI> {
        TODO("Not yet implemented")
    }

    override suspend fun getComingSoonMovies(): Response<MovieAPI> {
        TODO("Not yet implemented")
    }

    override suspend fun getTop250Movies(): Response<TopRatedMovieAPI> {
        TODO("Not yet implemented")
    }

    override suspend fun getBoxOfficeMovies(): Response<TopMoviesOfficeAPI> {
        TODO("Not yet implemented")
    }

    override suspend fun searchForMovieOrSeries(): Response<SearchResultAPI> {
        TODO("Not yet implemented")
    }
}