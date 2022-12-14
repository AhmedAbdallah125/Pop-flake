package com.ahmed_abdallah.pop_flake.ui.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed_abdallah.pop_flake.Utils.NetworkResponse
import com.ahmed_abdallah.pop_flake.Utils.ResultState
import com.ahmed_abdallah.pop_flake.Utils.ResultState.EmptyResult
import com.ahmed_abdallah.pop_flake.model.data.repository.IRepository
import com.ahmed_abdallah.pop_flake.pojo.SearchResult
import com.ahmed_abdallah.pop_flake.pojo.SearchResultAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {
    private val _resultList = MutableStateFlow<ResultState<List<SearchResult>>>(EmptyResult)
    val resultList get() = _resultList.asStateFlow()

    private val _showProgress: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgress = _showProgress.asStateFlow()
    fun searchForSeriesOrMovies(searchKey: StateFlow<String>) {
        viewModelScope.launch {
            _showProgress.emit(true)
            searchKey.debounce(100)
                .distinctUntilChanged().collect {
                    if (it.isNotEmpty()) {
                        search(it)
                    } else {
                        _resultList.emit(EmptyResult)
                        _showProgress.emit(false)
                    }
                }
        }
    }

    fun search(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _showProgress.emit(true)
            val res = async {
                repository.searchForMovieOrSeries(key)
            }
            sendResponseBack(res.await())
        }
    }

    private suspend fun sendResponseBack(response: NetworkResponse<SearchResultAPI>) {
        //indicator
        _showProgress.emit(true)

        when (response) {
            is NetworkResponse.FailureResponse -> {
                _showProgress.emit(false)
                _resultList.emit(ResultState.Error(response.errorString))
            }
            is NetworkResponse.SuccessResponse -> {
                _showProgress.emit(false)
                if (!response.data.results.isNullOrEmpty()) {
                    _resultList.emit(ResultState.Success(response.data.results))
                } else {
                    _resultList.emit(EmptyResult)
                }
            }
        }
    }

    fun closeSearch() {
        viewModelScope.launch {
            // circular
            _resultList.emit(EmptyResult)
        }
    }
}