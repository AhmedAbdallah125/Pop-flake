package com.ahmed_abdallah.pop_flake.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse
import com.ahmed_abdallah.pop_flake.Utils.ResultState
import com.ahmed_abdallah.pop_flake.domain.IHeaderUseCase
import com.ahmed_abdallah.pop_flake.model.data.repository.IRepository
import com.ahmed_abdallah.pop_flake.pojo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IRepository,
    private val headerUseCase: IHeaderUseCase
) : ViewModel() {
    private val _progressVisibility = MutableStateFlow(true)
    val progressVisibility get() = _progressVisibility.asStateFlow()

    private val _comingMovies = MutableStateFlow<ResultState<List<Movie>>>(ResultState.Loading)
    val comingMovies get() = _comingMovies.asStateFlow()

    private val _topRatedMovies =
        MutableStateFlow<ResultState<List<TopRatedMovie>>>(ResultState.Loading)
    val topRatedMovies get() = _topRatedMovies.asStateFlow()

    private val _boxOfficeMovies =
        MutableStateFlow<ResultState<List<BoxOfficeMovie>>>(ResultState.Loading)
    val boxOfficeMovies get() = _boxOfficeMovies.asStateFlow()

    private val _inTheatresMovies = MutableStateFlow<ResultState<List<Movie>>>(ResultState.Loading)
    val inTheatresMovies get() = _inTheatresMovies.asStateFlow()

    private val movieIdList =
        listOf("tt1375666", "tt7144666", "tt1745960", "tt8426926", "tt8041270")

    private val _headerShows =
        MutableStateFlow<ResultState<List<Pair<PosterAPI, Trailer>>>>(ResultState.Loading)
    val headerShows get() = _headerShows.asStateFlow()
    private val pairList = mutableListOf<Pair<PosterAPI, Trailer>>()
    fun getHeaderShows() {

        viewModelScope.launch(Dispatchers.IO) {
            for (id in movieIdList) {
                val res = async {
                    headerUseCase.searchForTrailerPoster(id)
                }
                sendHeaderResponseBack(res.await())
            }
            _headerShows.emit(ResultState.Success(pairList))
        }
    }

    private suspend fun sendHeaderResponseBack(
        response: NetworkResponse<Pair<PosterAPI, Trailer>>,
    ) {
        _headerShows.emit(ResultState.Loading)
        when (response) {
            is NetworkResponse.FailureResponse -> {
                _headerShows.emit(ResultState.Error(response.errorString))
            }
            is NetworkResponse.SuccessResponse -> {
                pairList.add(response.data)
            }
        }
    }

    fun getInTheatreMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = async {
                repository.getInTheatresMovies()
            }
            sendResponseBack(res.await(), _inTheatresMovies)
        }
    }

    private suspend fun sendResponseBack(
        response: NetworkResponse<MovieAPI>,
        mutableStateFlow: MutableStateFlow<ResultState<List<Movie>>>
    ) {
        mutableStateFlow.emit(ResultState.Loading)
        when (response) {
            is NetworkResponse.FailureResponse -> {
                mutableStateFlow.emit(ResultState.Error(response.errorString))
            }
            is NetworkResponse.SuccessResponse -> {
                if (response.data.items.isNotEmpty()) {
                    mutableStateFlow.emit(ResultState.Success(response.data.items))
                } else {
                    mutableStateFlow.emit(ResultState.EmptyResult)
                }
            }
        }
        _progressVisibility.emit(false)
    }

    fun getInComingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = async {
                repository.getComingSoonMovies()
            }
            sendResponseBack(res.await(), _comingMovies)
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = async {
                repository.getTop250Movies()
            }
            sendTopRatedResponseBack(res.await())
        }
    }

    private suspend fun sendTopRatedResponseBack(response: NetworkResponse<TopRatedMovieAPI>) {
        _topRatedMovies.emit(ResultState.Loading)
        when (response) {
            is NetworkResponse.FailureResponse -> {
                _topRatedMovies.emit(ResultState.Error(response.errorString))
            }
            is NetworkResponse.SuccessResponse -> {
                if (response.data.items.isNotEmpty()) {
                    _topRatedMovies.emit(ResultState.Success(response.data.items))
                } else {
                    _topRatedMovies.emit(ResultState.EmptyResult)
                }
            }
        }
        _progressVisibility.emit(false)
    }

    fun getInBoxOfficeMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = async {
                repository.getBoxOfficeMovies()
            }
            sendBoxOfficeResponseBack(res.await())
        }
    }

    private suspend fun sendBoxOfficeResponseBack(response: NetworkResponse<TopMoviesOfficeAPI>) {
        _boxOfficeMovies.emit(ResultState.Loading)
        when (response) {
            is NetworkResponse.FailureResponse -> {
                _boxOfficeMovies.emit(ResultState.Error(response.errorString))
            }
            is NetworkResponse.SuccessResponse -> {
                if (response.data.items.isNotEmpty()) {
                    _boxOfficeMovies.emit(ResultState.Success(response.data.items))
                } else {
                    _boxOfficeMovies.emit(ResultState.EmptyResult)
                }
            }
        }
        _progressVisibility.emit(false)
    }

    fun refreshData() {
        viewModelScope.launch {
            _headerShows.emit(ResultState.EmptyResult)
            _comingMovies.emit(ResultState.EmptyResult)
            _inTheatresMovies.emit(ResultState.EmptyResult)
            _topRatedMovies.emit(ResultState.EmptyResult)
            _boxOfficeMovies.emit(ResultState.EmptyResult)
            _progressVisibility.emit(true)
        }
        getInTheatreMovies()
        getHeaderShows()
        getInBoxOfficeMovies()
        getTopRatedMovies()
        getInComingMovies()
    }

}