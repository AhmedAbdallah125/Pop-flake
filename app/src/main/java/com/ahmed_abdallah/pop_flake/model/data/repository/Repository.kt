package com.ahmed_abdallah.pop_flake.model.data.repository

import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse
import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse.FailureResponse
import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse.SuccessResponse
import com.ahmed_abdallah.pop_flake.model.data.localSource.LocalSource
import com.ahmed_abdallah.pop_flake.model.data.remoteSource.RemoteSource
import com.ahmed_abdallah.pop_flake.pojo.*
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

private const val connectionFailure = "Bad Connection"

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : IRepository {
    override suspend fun getInTheatresMovies(): NetworkResponse<MovieAPI> {
        return try {
            val response = remoteSource.getInTheatresMovies()
            if (response.isSuccessful) {
                SuccessResponse(response.body() ?: MovieAPI(emptyList()))
            } else {
                parseError(response.errorBody())
            }
        } catch (ex: Exception) {
            FailureResponse(connectionFailure)
        }
    }

    override suspend fun getComingSoonMovies(): NetworkResponse<MovieAPI> {
        return try {
            val response = remoteSource.getComingSoonMovies()
            if (response.isSuccessful) {
                SuccessResponse(response.body() ?: MovieAPI(emptyList()))
            } else {
                parseError(response.errorBody())
            }
        } catch (ex: Exception) {
            FailureResponse(connectionFailure)
        }
    }

    override suspend fun getTop250Movies(): NetworkResponse<TopRatedMovieAPI> {
        return try {
            val response = remoteSource.getTop250Movies()
            if (response.isSuccessful) {
                SuccessResponse(response.body() ?: TopRatedMovieAPI(emptyList()))
            } else {
                parseError(response.errorBody())
            }
        } catch (ex: Exception) {
            FailureResponse(connectionFailure)
        }
    }

    override suspend fun getBoxOfficeMovies(): NetworkResponse<TopMoviesOfficeAPI> {
        return try {
            val response = remoteSource.getBoxOfficeMovies()
            if (response.isSuccessful) {
                SuccessResponse(response.body() ?: TopMoviesOfficeAPI(emptyList()))
            } else {
                parseError(response.errorBody())
            }
        } catch (ex: Exception) {
            FailureResponse(connectionFailure)
        }
    }

    override suspend fun searchForMovieOrSeries(
        searchKey: String
    ): NetworkResponse<SearchResultAPI> {
        return try {
            val response = remoteSource.searchForMovieOrSeries(searchKey)
            if (response.isSuccessful) {
                SuccessResponse(response.body() ?: SearchResultAPI(results = emptyList()))
            } else {
                parseError(response.errorBody())
            }
        } catch (ex: Exception) {
            FailureResponse(connectionFailure)
        }
    }

    override suspend fun searchForTrailer(searchKey: String): NetworkResponse<Trailer> {
        return try {
            val response = remoteSource.searchForTrailer(searchKey)
            if (response.isSuccessful) {
                SuccessResponse(response.body() ?: Trailer())
            } else {
                parseError(response.errorBody())
            }
        } catch (ex: Exception) {
            FailureResponse(connectionFailure)
        }
    }

    override suspend fun searchForPoster(searchKey: String): NetworkResponse<PosterAPI> {
        return try {
            val response = remoteSource.searchForPoster(searchKey)
            return if (response.isSuccessful) {
                SuccessResponse(response.body() ?: PosterAPI(posters = emptyList()))
            } else {
                parseError(response.errorBody())
            }
        } catch (ex: Exception) {
            FailureResponse(connectionFailure)
        }
    }

    override fun setMode(mode: Int) {
        localSource.setMode(mode)
    }

    override fun getMode() = localSource.getMode()


    private fun parseError(errorBody: ResponseBody?): FailureResponse {
        return errorBody?.let {
            val errorMessage = runCatching {
                JSONObject(it.string()).getString("errors")
            }
            return FailureResponse(errorMessage.getOrDefault("Empty Error"))
        } ?: FailureResponse("Null Error")
    }
}