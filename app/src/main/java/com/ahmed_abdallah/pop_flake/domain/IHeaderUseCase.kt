package com.ahmed_abdallah.pop_flake.domain

import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse
import com.ahmed_abdallah.pop_flake.pojo.PosterAPI
import com.ahmed_abdallah.pop_flake.pojo.Trailer

interface IHeaderUseCase {
    suspend fun searchForTrailerPoster(searchKey: String): NetworkResponse<Pair<PosterAPI, Trailer>>
}