package com.ahmed_abdallah.pop_flake.di

import com.ahmed_abdallah.pop_flake.domain.HeaderUseCase
import com.ahmed_abdallah.pop_flake.domain.IHeaderUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class HeaderUseCase {
    @Binds
    abstract fun bindHeaderUseCase(headerUseCase: HeaderUseCase): IHeaderUseCase
}