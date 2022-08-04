package com.ahmed_abdallah.pop_flake.ui.form.sharedViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed_abdallah.pop_flake.Utils.ResultState
import com.ahmed_abdallah.pop_flake.pojo.Complain
import com.ahmed_abdallah.pop_flake.ui.form.sharedViewModel.FormViewModel.AuthResult.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor() : ViewModel() {
    private val _complainList: ArrayList<Complain> = ArrayList()

    private val _complains = MutableStateFlow<ResultState<List<Complain>>>(ResultState.Loading)
    val complains = _complains.asStateFlow()

    private val _submitState: MutableStateFlow<AuthResult> = MutableStateFlow(Idle)
    val submitState = _submitState.asStateFlow()



    fun addComplain(complain: Complain) = _complainList.add(complain)

    fun getComplains() {
        viewModelScope.launch {
            _complains.emit(ResultState.Success(_complainList))
        }
    }

    fun submitComplain(complain: Complain) {
        viewModelScope.launch {
            if (complain.name.isEmpty()) {
                _submitState.emit(InvalidData(ErrorType.NameError))
            } else if (complain.complainString.isEmpty()) {
                _submitState.emit(InvalidData(ErrorType.ComplainError))

            } else {
                _submitState.emit(SubmitSuccess)
            }
        }
    }

    fun resetComplain() {
        viewModelScope.launch {
            _submitState.emit(Idle)
        }

    }

    enum class ErrorType {
        NameError, ComplainError
    }


    sealed class AuthResult {
        object Idle : AuthResult()
        data class InvalidData(val error: ErrorType) : AuthResult()
        object SubmitSuccess : AuthResult()
    }
}