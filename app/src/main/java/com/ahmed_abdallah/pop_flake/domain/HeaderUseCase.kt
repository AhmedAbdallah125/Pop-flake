package com.ahmed_abdallah.pop_flake.domain

import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse
import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse.FailureResponse
import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse.SuccessResponse
import com.ahmed_abdallah.pop_flake.model.data.repository.IRepository
import com.ahmed_abdallah.pop_flake.pojo.PosterAPI
import com.ahmed_abdallah.pop_flake.pojo.Trailer
import javax.inject.Inject

class HeaderUseCase @Inject constructor(private val repository: IRepository) : IHeaderUseCase {
    override suspend fun searchForTrailerPoster(searchKey: String): NetworkResponse<Pair<PosterAPI, Trailer>> {
        return when (val poster = repository.searchForPoster(searchKey)) {
            is FailureResponse -> FailureResponse(poster.errorString)
            is SuccessResponse -> {
                if (poster.data.posters.isEmpty()) FailureResponse("empty List")
                else {
                    return getTrailer(searchKey, poster.data)
                }
            }
        }

    }

    private suspend fun getTrailer(
        searchKey: String,
        posterAPI: PosterAPI
    ): NetworkResponse<Pair<PosterAPI, Trailer>> {
        return when (val trailer = repository.searchForTrailer(searchKey)) {
            is FailureResponse -> FailureResponse(trailer.errorString)
            is SuccessResponse -> {
                if (trailer.data.thumbnailUrl.isNullOrEmpty()) FailureResponse("empty List")
                else {
                    SuccessResponse(Pair(posterAPI, trailer.data))
                }
            }
        }
    }
}