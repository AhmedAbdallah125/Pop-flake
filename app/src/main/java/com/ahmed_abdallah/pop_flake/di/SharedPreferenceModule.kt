package com.ahmed_abdallah.pop_flake.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object SharedPreferenceModule {
    @Provides
    fun provideShared(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("ModeSetting", Context.MODE_PRIVATE)
    }

}