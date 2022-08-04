package com.ahmed_abdallah.pop_flake.di

import android.content.SharedPreferences
import com.ahmed_abdallah.pop_flake.model.data.localSource.ConcreteSource
import com.ahmed_abdallah.pop_flake.model.data.localSource.LocalSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalModule {
    @Binds
    abstract fun bindLocalSource(concreteSource: ConcreteSource): LocalSource
}