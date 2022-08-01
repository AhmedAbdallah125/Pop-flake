package com.ahmed_abdallah.pop_flake.model.data.remoteSource

import com.ahmed_abdallah.pop_flake.pojo.MovieAPI
import retrofit2.Response

class ConcreteRemote(private val retrofitServices: RetrofitServices) : RemoteSource {
    override suspend fun getInTheatresMovies():Response<MovieAPI> = retrofitServices.getInTheatresMovies()
}