package com.ahmed_abdallah.pop_flake.model.data.localSource

import android.content.SharedPreferences
import javax.inject.Inject

class ConcreteSource @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LocalSource {

    override fun setMode(mode: Int) {
        sharedPreferences.edit().apply {
            putInt("Mode", mode)
        }.apply()
    }

    override fun getMode() =
        sharedPreferences.getInt("Mode", 0)

}