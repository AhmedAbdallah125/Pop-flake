package com.ahmed_abdallah.pop_flake.ui.settting.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmed_abdallah.pop_flake.model.data.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {

    private val _mode: MutableLiveData<Int> = MutableLiveData()
    val mode = _mode as LiveData<Int>


    fun getMode() {
        _mode.postValue(repository.getMode())
    }

    fun setMode(mode: Int) {
        repository.setMode(mode)
    }




}