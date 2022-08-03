package com.ahmed_abdallah.pop_flake.di

import com.ahmed_abdallah.pop_flake.model.data.remoteSource.ConcreteRemote
import com.ahmed_abdallah.pop_flake.model.data.remoteSource.RemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteModule {
    @Binds
    abstract fun bindRemoteSource(remote: ConcreteRemote):RemoteSource

}