package com.ahmed_abdallah.pop_flake.model.data.remoteSource

import com.ahmed_abdallah.pop_flake.pojo.*
import retrofit2.Response
import javax.inject.Inject

class ConcreteRemote @Inject constructor(private val retrofitServices: RetrofitServices) :
    RemoteSource {
    override suspend fun getInTheatresMovies(): Response<MovieAPI> {
        return retrofitServices.getInTheatresMovies()
    }

    override suspend fun getComingSoonMovies(): Response<MovieAPI> {
        return retrofitServices.getComingSoonMovies()
    }

    override suspend fun getTop250Movies(): Response<TopRatedMovieAPI> {
        return retrofitServices.getTop250Movies()
    }

    override suspend fun getBoxOfficeMovies(): Response<TopMoviesOfficeAPI> {
        return retrofitServices.getBoxOfficeMovies()
    }

    override suspend fun searchForMovieOrSeries(searchKey: String): Response<SearchResultAPI> {
        return retrofitServices.searchForMovieOrSeries(searchKey = searchKey)
    }

    override suspend fun searchForTrailer(searchKey: String): Response<Trailer> {
        return retrofitServices.searchForTrailer(searchKey = searchKey)
    }

    override suspend fun searchForPoster(searchKey: String): Response<PosterAPI> {
        return retrofitServices.searchForPoster(searchKey = searchKey)
    }
}