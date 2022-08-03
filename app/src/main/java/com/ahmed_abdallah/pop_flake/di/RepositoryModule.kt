package com.ahmed_abdallah.pop_flake.di

import com.ahmed_abdallah.pop_flake.model.data.repository.IRepository
import com.ahmed_abdallah.pop_flake.model.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repository: Repository): IRepository
}