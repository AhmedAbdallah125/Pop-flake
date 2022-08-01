package com.ahmed_abdallah.pop_flake.model.data.remoteSource

import com.ahmed_abdallah.pop_flake.pojo.MovieAPI
import retrofit2.Response

interface RemoteSource {
    suspend fun getInTheatresMovies(): Response<MovieAPI>
}